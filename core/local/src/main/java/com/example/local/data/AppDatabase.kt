package com.example.local.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.anime.data.local.AnimeFavoriteDao
import com.example.anime.data.local.AnimeFavoriteEntity

@Database(entities = [AnimeFavoriteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun animeDao(): AnimeFavoriteDao
}