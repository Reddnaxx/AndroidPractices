package com.example.anime.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.anime.domain.models.AnimeImageInfo
import com.example.anime.domain.models.AnimeKind
import com.example.anime.domain.models.AnimeStatus

@Entity(tableName = "anime_favorites")
@TypeConverters(AnimeConverter::class)
data class AnimeFavoriteEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val russian: String,
    val image: AnimeImageInfo,
    val kind: AnimeKind?,
    val score: Float,
    val status: AnimeStatus?,
    val episodes: Int,
    val episodesAired: Int,
    val airedOn: String?,
    val releasedOn: String?
)
