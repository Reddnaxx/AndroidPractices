package com.example.urfuandroidpractice.listWithDetails.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AnimeFavoriteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun animeDao(): AnimeFavoriteDao
}