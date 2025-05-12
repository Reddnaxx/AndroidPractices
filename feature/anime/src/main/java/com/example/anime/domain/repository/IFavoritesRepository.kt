package com.example.anime.domain.repository

import com.example.anime.domain.models.AnimeShortModel

interface IFavoritesRepository {
    suspend fun addToFavorites(anime: AnimeShortModel)

    suspend fun getFavorites(): List<AnimeShortModel>
}