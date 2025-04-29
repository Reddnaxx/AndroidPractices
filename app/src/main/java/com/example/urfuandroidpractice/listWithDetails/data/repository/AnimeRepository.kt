package com.example.urfuandroidpractice.listWithDetails.data.repository

import com.example.urfuandroidpractice.listWithDetails.data.api.AnimeApi
import com.example.urfuandroidpractice.listWithDetails.domain.models.AnimeFullModel
import com.example.urfuandroidpractice.listWithDetails.domain.models.AnimeGenre
import com.example.urfuandroidpractice.listWithDetails.domain.models.AnimeShortModel
import com.example.urfuandroidpractice.listWithDetails.domain.repository.IAnimeRepository

class AnimeRepository(
    private val service: AnimeApi,
) : IAnimeRepository {

    override suspend fun getList(
        q: String,
        page: Int,
        limit: Int,
        genre: Int?
    ): List<AnimeShortModel> {
        return service.getList(query = q, page = page, genreId = genre)
    }

    override suspend fun getById(id: Int): AnimeFullModel {
        return service.getDetails(id)
    }

    override suspend fun getGenres(): List<AnimeGenre> {
        return service.getGenres().filter { it.entryType == "Anime" }
    }
}