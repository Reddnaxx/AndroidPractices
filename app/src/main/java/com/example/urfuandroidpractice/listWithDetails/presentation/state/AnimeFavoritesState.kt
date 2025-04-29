package com.example.urfuandroidpractice.listWithDetails.presentation.state

import com.example.urfuandroidpractice.listWithDetails.domain.models.AnimeShortModel

interface AnimeFavoritesState {
    val favorites: List<AnimeShortModel>
    val isRefreshing: Boolean
}