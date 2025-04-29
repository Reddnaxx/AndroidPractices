package com.example.urfuandroidpractice.listWithDetails.domain.repository

import com.example.urfuandroidpractice.listWithDetails.domain.models.AnimeFullModel
import com.example.urfuandroidpractice.listWithDetails.domain.models.AnimeGenre
import com.example.urfuandroidpractice.listWithDetails.domain.models.AnimeShortModel
import kotlinx.coroutines.flow.Flow

interface IAnimeRepository {
    fun getList(
        q: String = "",
        page: Int = 1,
        limit: Int = 50,
        genre: Int? = null
    ): Flow<List<AnimeShortModel>>

    fun getById(id: Int): Flow<AnimeFullModel?>

    fun getGenres(): Flow<List<AnimeGenre>>
}