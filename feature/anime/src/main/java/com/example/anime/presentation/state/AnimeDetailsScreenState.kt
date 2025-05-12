package com.example.anime.presentation.state

import com.example.anime.domain.models.AnimeFullModel

interface AnimeDetailsScreenState {
    val anime: AnimeFullModel?
    val userScore: Float
    val isUserScoreVisible: Boolean
    val isLoading: Boolean
    val error: String?
    val isError: Boolean
}