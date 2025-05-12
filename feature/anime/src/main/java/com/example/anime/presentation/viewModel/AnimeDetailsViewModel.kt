package com.example.anime.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anime.domain.models.AnimeFullModel
import com.example.anime.domain.repository.IAnimeRepository
import com.example.anime.presentation.state.AnimeDetailsScreenState
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.back
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
        mutableState.isLoading = true
        try {
            val animeDetail = repository.getById(id)
            mutableState.anime = animeDetail
        } catch (e: Throwable) {
            mutableState.error = e.message
        } finally {
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

    private class MutableAnimeDetailsState :
        AnimeDetailsScreenState {
        override var anime: AnimeFullModel? by mutableStateOf(null)
        override var userScore: Float by mutableFloatStateOf(0f)
        override val isUserScoreVisible: Boolean get() = userScore != 0f
        override var isLoading: Boolean by mutableStateOf(false)
        override var error: String? by mutableStateOf(null)
        override val isError: Boolean get() = error.isNullOrEmpty().not()
    }
}