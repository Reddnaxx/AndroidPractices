package com.example.anime.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anime.domain.models.AnimeGenre
import com.example.anime.domain.models.AnimeShortModel
import com.example.anime.domain.repository.IAnimeRepository
import com.example.anime.presentation.screens.AnimeDetailsScreen
import com.example.anime.presentation.state.AnimeListScreenState
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.forward
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AnimeListViewModel(
    private val repository: IAnimeRepository,
    private val navigation: StackNavContainer
) : ViewModel() {

    private val mutableState = MutableAnimeListState()
    val viewState: AnimeListScreenState = mutableState

    init {
        getGenres()
        onRefresh()
    }

    private fun loadAnime() {
        setLoadingState()

        viewModelScope.launch {
            try {
                repository.getList(
                    q = mutableState.query.value,
                    page = mutableState.page,
                    genre = mutableState.selectedGenre?.id
                ).let {
                    updateAnimeList(it)
                }
            } catch (e: Exception) {
                mutableState.error = e.message
            } finally {
                mutableState.isLoading = false
                mutableState.isLoadingMore = false
            }
        }
    }

    fun onItemClicked(id: Int) {
        navigation.forward(AnimeDetailsScreen(id))
    }

    fun onLoadMore() {
        mutableState.page++
        loadAnime()
    }

    fun onRefresh() {
        mutableState.page = 1
        loadAnime()
    }

    fun onQueryChanged(query: String) {
        mutableState.query.value = query
        mutableState.page = 1
        loadAnime()
    }

    fun onGenreSelected(genre: AnimeGenre?) {
        mutableState.selectedGenre = genre
        mutableState.page = 1
        loadAnime()
    }

    private fun setLoadingState() {
        mutableState.isLoading = mutableState.page == 1
        mutableState.isLoadingMore = mutableState.page > 1
    }

    private fun updateAnimeList(newItems: List<AnimeShortModel>) {
        mutableState.items = if (mutableState.page == 1) newItems else mutableState.items + newItems
        mutableState.isLoading = false
        mutableState.isLoadingMore = false
    }

    private fun getGenres() {
        viewModelScope.launch {
            repository.getGenres().let {
                mutableState.genres = it
            }
        }
    }

    class MutableAnimeListState : AnimeListScreenState {
        override var items by mutableStateOf(emptyList<AnimeShortModel>())
        override var genres by mutableStateOf(emptyList<AnimeGenre>())
        override var selectedGenre by mutableStateOf<AnimeGenre?>(
            null
        )
        override var page by mutableIntStateOf(1)
        override var query = MutableStateFlow("")
        override val isEmpty get() = items.isEmpty()
        override var isLoading by mutableStateOf(false)
        override var isLoadingMore by mutableStateOf(false)
        override var error: String? by mutableStateOf(null)
        override val isError: Boolean get() = error.isNullOrEmpty().not()
    }
}
