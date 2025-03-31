package com.example.urfuandroidpractice.listWithDetails.data.repository

import com.example.urfuandroidpractice.listWithDetails.data.api.AnimeService
import com.example.urfuandroidpractice.listWithDetails.domain.entity.AnimeFullEntity
import com.example.urfuandroidpractice.listWithDetails.domain.entity.AnimeShortEntity
import com.example.urfuandroidpractice.listWithDetails.domain.repository.IAnimeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class AnimeRepository(
    private val service: AnimeService
) : IAnimeRepository {

    override fun getList(q: String, page: Int): Flow<List<AnimeShortEntity>> = flow {
        val response = withContext(Dispatchers.IO) {
            service.getList(query = q, page = page).execute().body() ?: emptyList()
        }
        emit(response)
    }

    override fun getById(id: Int): Flow<AnimeFullEntity?> = flow {
        val response = withContext(Dispatchers.IO) {
            service.getDetails(id).execute().body()
        }
        emit(response)
    }
}