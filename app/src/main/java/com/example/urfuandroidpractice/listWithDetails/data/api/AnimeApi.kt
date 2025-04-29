package com.example.urfuandroidpractice.listWithDetails.data.api

import com.example.urfuandroidpractice.listWithDetails.domain.models.AnimeFullModel
import com.example.urfuandroidpractice.listWithDetails.domain.models.AnimeGenre
import com.example.urfuandroidpractice.listWithDetails.domain.models.AnimeShortModel
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeApi {

    @GET("animes")
    suspend fun getList(
        @Query("q") query: String = "",
        @Query("order") orderBy: String = "popularity",
        @Query("limit") limit: Int = 50,
        @Query("page") page: Int = 1,
        @Query("status") status: String = "released",
        @Query("genre") genreId: Int? = null
    ): List<AnimeShortModel>

    @GET("animes/{id}")
    suspend fun getDetails(@Path("id") id: Int): AnimeFullModel

    @GET("genres")
    suspend fun getGenres(): List<AnimeGenre>

    companion object {
        fun create(retrofit: Retrofit): AnimeApi {
            return retrofit.create(AnimeApi::class.java)
        }
    }
}