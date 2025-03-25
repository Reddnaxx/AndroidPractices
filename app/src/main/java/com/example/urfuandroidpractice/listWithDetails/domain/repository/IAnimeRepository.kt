package com.example.urfuandroidpractice.listWithDetails.domain.repository

import com.example.urfuandroidpractice.listWithDetails.domain.entity.AnimeFullEntity
import com.example.urfuandroidpractice.listWithDetails.domain.entity.AnimeShortEntity

interface IAnimeRepository {
    fun getList(q: String = ""): List<AnimeShortEntity>

    fun getById(id: Int): AnimeFullEntity?
}