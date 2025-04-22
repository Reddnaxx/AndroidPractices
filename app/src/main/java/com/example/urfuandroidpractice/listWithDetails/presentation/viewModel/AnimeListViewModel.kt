package com.example.urfuandroidpractice.listWithDetails.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.urfuandroidpractice.listWithDetails.domain.entity.AnimeGenre
import com.example.urfuandroidpractice.listWithDetails.domain.entity.AnimeShortEntity
import com.example.urfuandroidpractice.listWithDetails.domain.repository.IAnimeRepository
import com.example.urfuandroidpractice.listWithDetails.presentation.screens.AnimeDetailsScreen
import com.example.urfuandroidpractice.listWithDetails.presentation.state.AnimeListScreenState
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.forward
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class AnimeListViewModel(
    private val repository: IAnimeRepository,
    private val navigation: StackNavContainer
) : ViewModel() {

    private val mutableState = MutableAnimeListState()
    val viewState: AnimeListScreenState = mutableState

    @OptIn(ExperimentalCoroutinesApi::class)
    private val searchFlow = mutableState.query.flatMapLatest { query ->
        repository.getList(
            q = query,
            page = mutableState.page,
            genre = mutableState.selectedGenre?.id
        )
    }

    init {
        getGenres()
        onRefresh()
        observeSearchResults()
    }

    private fun loadAnime() {
        viewModelScope.launch {
            repository.getList(
                q = mutableState.query.value,
                page = mutableState.page,
                genre = mutableState.selectedGenre?.id
            )
                .onStart { setLoadingState() }
                .catch { mutableState.error = it.message }
                .collectLatest { updateAnimeList(it) }
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
    }

    fun onGenreSelected(genre: AnimeGenre?) {
        mutableState.selectedGenre = genre
        mutableState.page = 1
        loadAnime()
    }

    private fun observeSearchResults() {
        viewModelScope.launch {
            searchFlow.onStart { mutableState.isLoading = true }
                .catch { mutableState.error = it.message }.collectLatest {
                    updateAnimeList(it)
                    mutableState.page = 1
                }
        }
    }

    private fun setLoadingState() {
        mutableState.isLoading = mutableState.page == 1
        mutableState.isLoadingMore = mutableState.page > 1
    }

    private fun updateAnimeList(newItems: List<AnimeShortEntity>) {
        mutableState.items = if (mutableState.page == 1) newItems else mutableState.items + newItems
        mutableState.isLoading = false
        mutableState.isLoadingMore = false
    }

    private fun getGenres() {
        viewModelScope.launch {
            repository.getGenres()
                .collect { mutableState.genres = it }
        }
    }

    class MutableAnimeListState : AnimeListScreenState {
        override var items by mutableStateOf(emptyList<AnimeShortEntity>())
        override var genres by mutableStateOf(emptyList<AnimeGenre>())
        override var selectedGenre by mutableStateOf<AnimeGenre?>(null)
        override var page by mutableIntStateOf(1)
        override var query = MutableStateFlow("")
        override val isEmpty get() = items.isEmpty()
        override var isLoading by mutableStateOf(false)
        override var isLoadingMore by mutableStateOf(false)
        override var error: String? by mutableStateOf(null)
    }
}
