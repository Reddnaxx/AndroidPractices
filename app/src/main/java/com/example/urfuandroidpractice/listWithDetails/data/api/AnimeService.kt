package com.example.urfuandroidpractice.listWithDetails.data.api

import com.example.urfuandroidpractice.listWithDetails.domain.entity.AnimeFullEntity
import com.example.urfuandroidpractice.listWithDetails.domain.entity.AnimeShortEntity
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeService {

    @GET("animes?order=popularity&limit=50&status=released")
    fun getList(
        @Query("q") query: String = "",
        @Query("order") orderBy: String = "popularity",
        @Query("limit") limit: Int = 50,
        @Query("page") page: Int = 1,
        @Query("status") status: String = "released"
    ): Call<List<AnimeShortEntity>>

    @GET("animes/{id}")
    fun getDetails(@Path("id") id: Int): Call<AnimeFullEntity>

    companion object {

        fun create(retrofit: Retrofit): AnimeService {
            return retrofit.create(AnimeService::class.java)
        }
    }
}