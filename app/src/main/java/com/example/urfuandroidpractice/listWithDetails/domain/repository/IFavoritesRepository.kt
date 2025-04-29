package com.example.urfuandroidpractice.listWithDetails.domain.repository

import com.example.urfuandroidpractice.listWithDetails.domain.models.AnimeShortModel

interface IFavoritesRepository {
    suspend fun addToFavorites(anime: AnimeShortModel)

    suspend fun getFavorites(): List<AnimeShortModel>
}