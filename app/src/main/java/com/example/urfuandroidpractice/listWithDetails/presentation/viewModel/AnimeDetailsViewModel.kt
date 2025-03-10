package com.example.urfuandroidpractice.listWithDetails.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.urfuandroidpractice.listWithDetails.domain.entity.AnimeFullEntity
import com.example.urfuandroidpractice.listWithDetails.domain.repository.IAnimeRepository
import com.example.urfuandroidpractice.listWithDetails.presentation.state.AnimeDetailsScreenState
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.back

class AnimeDetailsViewModel(
    private val repository: IAnimeRepository,
    private val navigation: StackNavContainer,
    private val id: Int
) : ViewModel() {
    private val mutableState = MutableAnimeDetailsState()
    val viewState = mutableState as AnimeDetailsScreenState

    init {
        mutableState.anime = repository.getById(id)
    }

    fun back() {
        navigation.back()
    }

    fun onRatingChanged(userScore: Float) {
        mutableState.userScore = userScore
    }

    private class MutableAnimeDetailsState : AnimeDetailsScreenState {
        override var anime: AnimeFullEntity? by mutableStateOf(null)
        override var userScore: Float by mutableFloatStateOf(0f)
        override val isUserScoreVisible: Boolean get() = userScore != 0f
    }
}