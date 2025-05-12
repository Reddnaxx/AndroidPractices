package com.example.local.di

import android.content.Context
import androidx.room.Room
import com.example.anime.data.local.AnimeFavoriteDao
import com.example.local.data.AppDatabase
import org.koin.dsl.module

val localModule = module {
    single<AppDatabase> { provideDatabase(get()) }
    single<AnimeFavoriteDao> { provideAnimeDao(get()) }

}

private fun provideDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "anime_database"
    ).build()
}

fun provideAnimeDao(database: AppDatabase): AnimeFavoriteDao {
    return database.animeDao()
}