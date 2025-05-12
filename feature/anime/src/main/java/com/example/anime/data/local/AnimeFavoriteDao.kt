package com.example.anime.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AnimeFavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(anime: AnimeFavoriteEntity)

    @Query("DELETE FROM anime_favorites WHERE id = :animeId")
    suspend fun deleteAnime(animeId: Int)

    @Query("SELECT * FROM anime_favorites")
    fun getFavorites(): List<AnimeFavoriteEntity>
}
