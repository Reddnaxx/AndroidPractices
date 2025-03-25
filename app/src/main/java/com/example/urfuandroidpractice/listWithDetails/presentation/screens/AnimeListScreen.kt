package com.example.urfuandroidpractice.listWithDetails.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.example.urfuandroidpractice.R
import com.example.urfuandroidpractice.listWithDetails.data.mappers.DateMapper
import com.example.urfuandroidpractice.listWithDetails.data.mock.AnimeData
import com.example.urfuandroidpractice.listWithDetails.domain.entity.AnimeShortEntity
import com.example.urfuandroidpractice.listWithDetails.presentation.viewModel.AnimeListViewModel
import com.example.urfuandroidpractice.ui.theme.Spacing
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.LocalStackNavigation
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import java.time.format.DateTimeFormatter


@Parcelize
class AnimeListScreen(
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content(modifier: Modifier) {
        val navigation = LocalStackNavigation.current

        val viewModel = koinViewModel<AnimeListViewModel> { parametersOf(navigation) }
        val state = viewModel.viewState

        Scaffold(
            modifier = modifier,
            topBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(horizontal = Spacing.large)
                        .padding(bottom = Spacing.medium),
                ) {
                    SearchBar(
                        colors = SearchBarDefaults.colors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        inputField = {
                            SearchBarDefaults.InputField(
                                query = state.query,
                                onQueryChange = { viewModel.onQueryChanged(it) },
                                placeholder = { Text("Поиск") },
                                onSearch = { },
                                expanded = false,
                                onExpandedChange = {},
                                leadingIcon = {
                                    Icon(
                                        Icons.Default.Search,
                                        contentDescription = null
                                    )
                                },
                            )
                        },
                        expanded = false,
                        onExpandedChange = { }
                    ) {}
                }
            }
        ) { innerPadding ->
            ListScreenContent(
                modifier = Modifier.padding(innerPadding),
                animeList = state.items,
                onItemClick = { id -> viewModel.onItemClicked(id) }
            )
        }
    }
}

@Composable
private fun ListScreenContent(
    modifier: Modifier = Modifier,
    animeList: List<AnimeShortEntity>,
    onItemClick: (id: Int) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(animeList) {
            AnimeListItem(
                anime = it,
                onClick = onItemClick
            )
        }
    }
}

@Composable
private fun AnimeListItem(
    anime: AnimeShortEntity,
    modifier: Modifier = Modifier,
    onClick: (id: Int) -> Unit
) {
    val context = LocalContext.current
    val date = DateMapper.toLocalString(anime.airedOn, DateTimeFormatter.ISO_DATE, "dd MMMM yyyy")

    Row(
        modifier = modifier
            .clickable { onClick(anime.id) }
            .padding(Spacing.medium)
            .fillMaxWidth(),
    ) {
        AsyncImage(
            model = stringResource(R.string.base_url) + anime.image.preview,
            contentDescription = anime.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .weight(2f)
                .aspectRatio(1f)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(Spacing.medium))

        Column(modifier = Modifier.weight(9f)) {
            Text(
                text = "${anime.russian} / ${anime.name}",
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = anime.kind.getString(context),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = date,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun AnimeListItemPreview() {
    AnimeListItem(anime = AnimeData.animeShort[0], onClick = {})
}