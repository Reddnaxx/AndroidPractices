package com.example.anime.domain.repository

import com.example.anime.domain.models.AnimeFullModel
import com.example.anime.domain.models.AnimeGenre
import com.example.anime.domain.models.AnimeShortModel

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