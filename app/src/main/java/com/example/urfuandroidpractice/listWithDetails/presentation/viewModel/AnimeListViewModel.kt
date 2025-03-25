package com.example.urfuandroidpractice.listWithDetails.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.urfuandroidpractice.listWithDetails.domain.entity.AnimeShortEntity
import com.example.urfuandroidpractice.listWithDetails.domain.repository.IAnimeRepository
import com.example.urfuandroidpractice.listWithDetails.presentation.screens.AnimeDetailsScreen
import com.example.urfuandroidpractice.listWithDetails.presentation.state.AnimeListScreenState
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.forward

class AnimeListViewModel(
    private val repository: IAnimeRepository,
    private val navigation: StackNavContainer
) : ViewModel() {

    private val mutableState = MutableAnimeListState()
    val viewState = mutableState as AnimeListScreenState

    init {
        loadAnime()
    }

    private fun loadAnime() {
        mutableState.items = repository.getList(viewState.query)
    }

    fun onQueryChanged(query: String) {
        mutableState.query = query
        loadAnime()
    }

    fun onItemClicked(id: Int) {
        navigation.forward(AnimeDetailsScreen(id))
    }

    class MutableAnimeListState : AnimeListScreenState {
        override var items by mutableStateOf(emptyList<AnimeShortEntity>())
        override var query by mutableStateOf("")
        override val isEmpty get() = items.isEmpty()
    }
}