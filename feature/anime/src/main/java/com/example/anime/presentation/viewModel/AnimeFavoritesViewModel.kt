package com.example.anime.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anime.domain.models.AnimeShortModel
import com.example.anime.domain.repository.IFavoritesRepository
import com.example.anime.presentation.screens.AnimeDetailsScreen
import com.example.anime.presentation.state.AnimeFavoritesState
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.forward
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnimeFavoritesViewModel(
    private val repository: IFavoritesRepository,
    private val navigation: StackNavContainer
) : ViewModel() {
    private val mutableState = MutableAnimeFavoritesState()
    val viewState = mutableState as AnimeFavoritesState

    init {
        loadFavorites()
    }

    fun loadFavorites() {
        viewModelScope.launch {
            mutableState.isRefreshing = true

            val favs = withContext(Dispatchers.IO) {
                repository.getFavorites()
            }

            mutableState.favorites = favs
            mutableState.isRefreshing = false
        }
    }

    fun onFavoriteClick(item: AnimeShortModel) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.addToFavorites(item)
            }
            loadFavorites()
        }
    }

    fun onItemClick(item: AnimeShortModel) {
        navigation.forward(AnimeDetailsScreen(item.id))
    }

    class MutableAnimeFavoritesState : AnimeFavoritesState {
        override var favorites: List<AnimeShortModel> by mutableStateOf(
            emptyList()
        )
        override var isRefreshing: Boolean by mutableStateOf(false)
    }
}