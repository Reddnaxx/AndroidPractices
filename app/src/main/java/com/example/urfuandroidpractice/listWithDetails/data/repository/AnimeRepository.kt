package com.example.urfuandroidpractice.listWithDetails.data.repository

import com.example.urfuandroidpractice.listWithDetails.data.api.AnimeApi
import com.example.urfuandroidpractice.listWithDetails.domain.models.AnimeFullModel
import com.example.urfuandroidpractice.listWithDetails.domain.models.AnimeGenre
import com.example.urfuandroidpractice.listWithDetails.domain.models.AnimeShortModel
import com.example.urfuandroidpractice.listWithDetails.domain.repository.IAnimeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class AnimeRepository(
    private val service: AnimeApi,
) : IAnimeRepository {

    override fun getList(
        q: String,
        page: Int,
        limit: Int,
        genre: Int?
    ): Flow<List<AnimeShortModel>> = flow {
        val response = withContext(Dispatchers.IO) {
            service.getList(query = q, page = page, genreId = genre)
        }
        emit(response)
    }

    override fun getById(id: Int): Flow<AnimeFullModel?> = flow {
        val response = withContext(Dispatchers.IO) {
            service.getDetails(id)
        }
        emit(response)
    }

    override fun getGenres(): Flow<List<AnimeGenre>> = flow {
        val response = withContext(Dispatchers.IO) {
            service.getGenres()
        }
        emit(response.filter {
            it.entryType == "Anime"
        })
    }
}