package com.example.urfuandroidpractice.listWithDetails.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.urfuandroidpractice.R
import com.example.urfuandroidpractice.listWithDetails.domain.models.AnimeShortModel
import com.example.urfuandroidpractice.listWithDetails.presentation.viewModel.AnimeFavoritesViewModel
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.LocalStackNavigation
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Parcelize
class AnimeFavoritesScreen(
    override val screenKey: ScreenKey = generateScreenKey(),
) : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content(modifier: Modifier) {
        val navigation = LocalStackNavigation.current

        val viewModel = koinViewModel<AnimeFavoritesViewModel> { parametersOf(navigation) }
        val state = viewModel.viewState

        Scaffold(
            modifier = modifier,
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.pages_favorites)) }
                )
            }
        ) { contentPadding ->
            PullToRefreshBox(
                isRefreshing = state.isRefreshing,
                onRefresh = { viewModel.loadFavorites() },
            ) {
                FavoritesContent(
                    modifier = Modifier.padding(contentPadding),
                    favorites = state.favorites,
                    onItemClick = { viewModel.onItemClick(it) },
                    onFavoriteClick = { viewModel.onFavoriteClick(it) },
                )
            }
        }
    }

    @Composable
    private fun FavoritesContent(
        modifier: Modifier,
        favorites: List<AnimeShortModel> = emptyList(),
        onItemClick: (AnimeShortModel) -> Unit,
        onFavoriteClick: (AnimeShortModel) -> Unit,
    ) {
        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(favorites) { anime ->
                AnimeListItem(
                    anime = anime,
                    onClick = { onItemClick(anime) },
                    onFavoriteClick = { onFavoriteClick(anime) },
                )
            }
        }
    }
}