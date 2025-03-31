package com.example.urfuandroidpractice.listWithDetails.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.urfuandroidpractice.listWithDetails.domain.entity.AnimeFullEntity
import com.example.urfuandroidpractice.listWithDetails.domain.repository.IAnimeRepository
import com.example.urfuandroidpractice.listWithDetails.presentation.state.AnimeDetailsScreenState
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.back
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch

class AnimeDetailsViewModel(
    private val repository: IAnimeRepository,
    private val navigation: StackNavContainer,
    private val id: Int
) : ViewModel() {
    private val mutableState = MutableAnimeDetailsState()
    val viewState = mutableState as AnimeDetailsScreenState

    init {
        onRefresh()
    }

    private suspend fun loadAnime() {
        repository.getById(id)
            .onStart { mutableState.isLoading = true }
            .catch { error -> mutableState.error = error.message }
            .shareIn(viewModelScope, SharingStarted.WhileSubscribed(5000), replay = 1)
            .collect {
                mutableState.anime = it
                mutableState.isLoading = false
            }
    }

    fun back() {
        navigation.back()
    }

    fun onRatingChanged(userScore: Float) {
        mutableState.userScore = userScore
    }

    fun onRefresh() {
        viewModelScope.launch {
            loadAnime()
        }
    }

    private class MutableAnimeDetailsState : AnimeDetailsScreenState {
        override var anime: AnimeFullEntity? by mutableStateOf(null)
        override var userScore: Float by mutableFloatStateOf(0f)
        override val isUserScoreVisible: Boolean get() = userScore != 0f
        override var isLoading: Boolean by mutableStateOf(false)
        override var error: String? by mutableStateOf(null)
    }
}