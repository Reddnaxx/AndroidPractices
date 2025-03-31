package com.example.urfuandroidpractice.listWithDetails.domain.repository

import com.example.urfuandroidpractice.listWithDetails.domain.entity.AnimeFullEntity
import com.example.urfuandroidpractice.listWithDetails.domain.entity.AnimeShortEntity
import kotlinx.coroutines.flow.Flow

interface IAnimeRepository {
    fun getList(q: String = "", page: Int = 1): Flow<List<AnimeShortEntity>>

    fun getById(id: Int): Flow<AnimeFullEntity?>
}