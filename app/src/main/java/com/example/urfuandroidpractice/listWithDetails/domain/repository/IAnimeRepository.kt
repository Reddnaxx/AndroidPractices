package com.example.urfuandroidpractice.listWithDetails.domain.repository

import com.example.urfuandroidpractice.listWithDetails.domain.entity.AnimeFullEntity
import com.example.urfuandroidpractice.listWithDetails.domain.entity.AnimeGenre
import com.example.urfuandroidpractice.listWithDetails.domain.entity.AnimeShortEntity
import kotlinx.coroutines.flow.Flow

interface IAnimeRepository {
    fun getList(q: String = "", page: Int = 1, genre: Int?): Flow<List<AnimeShortEntity>>

    fun getById(id: Int): Flow<AnimeFullEntity?>

    fun getGenres(): Flow<List<AnimeGenre>>
}