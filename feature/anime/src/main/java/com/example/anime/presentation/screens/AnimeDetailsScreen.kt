package com.example.anime.presentation.screens

import android.content.Context
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.anime.R
import com.example.anime.data.mock.AnimeData
import com.example.anime.domain.models.AnimeFullModel
import com.example.anime.presentation.components.RatingBar
import com.example.anime.presentation.components.TitledColumn
import com.example.anime.presentation.state.AnimeDetailsScreenState
import com.example.anime.presentation.viewModel.AnimeDetailsViewModel
import com.example.theme.Spacing
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.LocalStackNavigation
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Parcelize
class AnimeDetailsScreen(
    private val id: Int,
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content(modifier: Modifier) {
        val navigation = LocalStackNavigation.current

        val viewModel = koinViewModel<AnimeDetailsViewModel> { parametersOf(navigation, id) }
        val state = viewModel.viewState

        Scaffold(topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { viewModel.back() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            contentDescription = stringResource(R.string.desc_back)
                        )
                    }
                },
                title = { Text(stringResource(R.string.anime)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }, floatingActionButton = {
            FloatingActionButton(
                onClick = { },
            ) {
                Icon(
                    painterResource(R.drawable.comment),
                    modifier = Modifier.size(32.dp),
                    contentDescription = "Write Comment"
                )
            }
        }) { innerPadding ->
            PullToRefreshBox(
                isRefreshing = state.isLoading,
                onRefresh = { viewModel.onRefresh() },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                when {
                    state.isError -> ErrorScreen(state.error ?: "Unknown error")
                    state.isLoading -> LoadingScreen()
                    else -> AnimeDetailsContent(
                        state = state,
                        onRatingChanged = { viewModel.onRatingChanged(it) },
                        modifier = Modifier.verticalScroll(ScrollState(0))
                    )
                }
            }
        }
    }
}

@Composable
private fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorScreen(error: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Error: $error")
    }
}

@Composable
private fun AnimeDetailsContent(
    state: AnimeDetailsScreenState, onRatingChanged: (Float) -> Unit, modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val anime = state.anime ?: return Box(modifier = modifier) {
        Text("Anime not found")
    }

    Column(modifier = modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            "${anime.russian} / ${anime.name}", style = MaterialTheme.typography.titleLarge
        )
        Row(horizontalArrangement = Arrangement.spacedBy(Spacing.medium)) {
            AsyncImage(
                model = stringResource(R.string.base_url) + anime.image.original,
                contentDescription = anime.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(2f)
                    .clip(RoundedCornerShape(8.dp))
            )
            AnimeInfo(Modifier.weight(3f), anime, context)
        }

        AnimeRating(state, onRatingChanged)

        anime.description?.let {
            AnimeDescription(it)
        }
    }
}

@Composable
private fun AnimeDescription(text: String) {
    TitledColumn(title = stringResource(R.string.desc)) {
        Text(
            text, style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun AnimeRating(
    state: AnimeDetailsScreenState, onRatingChanged: (Float) -> Unit
) {
    TitledColumn(
        title = "Поставьте оценку",
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            RatingBar(
                rating = state.userScore,
                onRatingChanged = onRatingChanged,
            )

            if (state.isUserScoreVisible) {
                Text(
                    text = stringResource(R.string.your_score, state.userScore),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

@Composable
private fun AnimeInfo(
    modifier: Modifier = Modifier,
    anime: AnimeFullModel,
    context: Context,
) {

    TitledColumn(title = "Информация", modifier = modifier) {
        AnimeInformationRow("Тип", anime.kind?.getString(context) ?: "TV Сериал")
        AnimeInformationRow("Статус", anime.status?.getString(context) ?: "Вышло")
        AnimeInformationRow("Эпизоды", "${anime.episodesAired} / ${anime.episodes}")
        AnimeInformationRow("Длительность", "${anime.duration} мин")
        AnimeInformationRow("Рейтинг", anime.rating.uppercase())

        if (anime.genres.isNotEmpty()) {
            AnimeInformationRow("Жанры", anime.genres.joinToString { it.russian })
        }
    }
}

@Composable
fun AnimeInformationRow(name: String, value: String) {
    Row(verticalAlignment = Alignment.Top) {
        Text(
            text = "$name: ",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(2.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun AnimeDetailsContentPreview() {
    AnimeDetailsContent(state = object : AnimeDetailsScreenState {
        override val anime: AnimeFullModel = AnimeData.animeFull[0]
        override val userScore: Float = 0f
        override val isUserScoreVisible: Boolean = true
        override val isLoading: Boolean = false
        override val error: String? = null
        override val isError: Boolean = false
    }, onRatingChanged = { })
}