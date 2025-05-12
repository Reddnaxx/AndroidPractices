package com.example.anime.presentation.state

import com.example.anime.domain.models.AnimeShortModel

interface AnimeFavoritesState {
    val favorites: List<AnimeShortModel>
    val isRefreshing: Boolean
}