package com.example.urfuandroidpractice.listWithDetails.data.repository

import com.example.urfuandroidpractice.listWithDetails.data.mock.AnimeData
import com.example.urfuandroidpractice.listWithDetails.domain.entity.AnimeFullEntity
import com.example.urfuandroidpractice.listWithDetails.domain.entity.AnimeShortEntity
import com.example.urfuandroidpractice.listWithDetails.domain.repository.IAnimeRepository

class AnimeRepository : IAnimeRepository {
    override fun getList(q: String): List<AnimeShortEntity> =
        AnimeData.animeShort.filter { it.name.contains(q, ignoreCase = true) }

    override fun getById(id: Int): AnimeFullEntity? = AnimeData.animeFull.find { it.id == id }
}