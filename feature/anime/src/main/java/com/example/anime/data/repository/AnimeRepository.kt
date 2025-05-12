package com.example.anime.data.repository

import com.example.anime.data.api.AnimeApi
import com.example.anime.domain.models.AnimeFullModel
import com.example.anime.domain.models.AnimeGenre
import com.example.anime.domain.models.AnimeShortModel
import com.example.anime.domain.repository.IAnimeRepository


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