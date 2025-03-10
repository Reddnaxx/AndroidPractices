package com.example.urfuandroidpractice.listWithDetails.presentation.screens

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import com.example.urfuandroidpractice.R
import com.example.urfuandroidpractice.listWithDetails.data.mock.AnimeData
import com.example.urfuandroidpractice.listWithDetails.domain.entity.AnimeFullEntity
import com.example.urfuandroidpractice.listWithDetails.presentation.state.AnimeDetailsScreenState
import com.example.urfuandroidpractice.listWithDetails.presentation.viewModel.AnimeDetailsViewModel
import com.example.urfuandroidpractice.ui.components.RatingBar
import com.example.urfuandroidpractice.ui.components.TitledColumn
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

        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = { viewModel.back() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                tint = MaterialTheme.colorScheme.onPrimary,
                                contentDescription = "Back"
                            )
                        }
                    },
                    title = { Text("Аниме") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                AnimeDetailsContent(
                    state,
                    onRatingChanged = { value -> viewModel.onRatingChanged(value) },
                    modifier = Modifier.verticalScroll(ScrollState(0))
                )

                FloatingActionButton(
                    onClick = { },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                ) {
                    Icon(
                        painterResource(R.drawable.comment),
                        modifier = Modifier.size(32.dp),
                        contentDescription = "Write Comment"
                    )
                }
            }
        }
    }
}

@Composable
private fun AnimeDetailsContent(
    state: AnimeDetailsScreenState,
    onRatingChanged: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val anime = state.anime ?: return Box(modifier = modifier) {
        Text("Anime not found")
    }

    Column(modifier = modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            "${anime.russian} / ${anime.name}",
            style = MaterialTheme.typography.titleLarge
        )
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
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

        AnimeDescription(anime)
    }
}

@Composable
private fun AnimeDescription(anime: AnimeFullEntity) {
    TitledColumn(title = "Описание") {
        Text(
            anime.description,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun AnimeRating(
    state: AnimeDetailsScreenState,
    onRatingChanged: (Float) -> Unit
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
                    text = "Ваша оценка: ${state.userScore}",
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
    anime: AnimeFullEntity,
    context: Context,
) {
    TitledColumn(title = "Информация", modifier = modifier) {
        AnimeInformationRow("Тип", anime.kind.getString(context))
        AnimeInformationRow("Статус", anime.status.getString(context))
        AnimeInformationRow("Эпизоды", "${anime.episodesAired} / ${anime.episodes}")
        AnimeInformationRow("Длительность", "${anime.duration} мин")
        AnimeInformationRow("Рейтинг", anime.rating.uppercase())
        AnimeInformationRow("Жанры", anime.genres.joinToString(", "))
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
    AnimeDetailsContent(
        state = object : AnimeDetailsScreenState {
            override val anime: AnimeFullEntity = AnimeData.animeFull[0]
            override val userScore: Float = 0f
            override val isUserScoreVisible: Boolean = true
        },
        onRatingChanged = {},
        modifier = Modifier.fillMaxSize()
    )
}