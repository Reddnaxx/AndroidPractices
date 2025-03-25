package com.example.urfuandroidpractice.listWithDetails.presentation.state

import com.example.urfuandroidpractice.listWithDetails.domain.entity.AnimeFullEntity

interface AnimeDetailsScreenState {
    val anime: AnimeFullEntity?
    val userScore: Float
    val isUserScoreVisible: Boolean
}