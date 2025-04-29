package com.example.urfuandroidpractice.listWithDetails.presentation.state

import com.example.urfuandroidpractice.listWithDetails.domain.models.AnimeFullModel

interface AnimeDetailsScreenState {
    val anime: AnimeFullModel?
    val userScore: Float
    val isUserScoreVisible: Boolean
    val isLoading: Boolean
    val error: String?
    val isError: Boolean
}