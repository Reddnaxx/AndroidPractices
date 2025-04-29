package com.example.urfuandroidpractice.listWithDetails.domain.repository

import com.example.urfuandroidpractice.listWithDetails.domain.models.AnimeFullModel
import com.example.urfuandroidpractice.listWithDetails.domain.models.AnimeGenre
import com.example.urfuandroidpractice.listWithDetails.domain.models.AnimeShortModel

interface IAnimeRepository {
    suspend fun getList(
        q: String = "",
        page: Int = 1,
        limit: Int = 50,
        genre: Int? = null
    ): List<AnimeShortModel>

    suspend fun getById(id: Int): AnimeFullModel?

    suspend fun getGenres(): List<AnimeGenre>
}