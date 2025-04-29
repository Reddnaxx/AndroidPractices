package com.example.urfuandroidpractice.listWithDetails.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.urfuandroidpractice.listWithDetails.domain.models.AnimeKind
import com.example.urfuandroidpractice.listWithDetails.domain.models.AnimeStatus
import com.example.urfuandroidpractice.listWithDetails.domain.models.ImageInfo

@Entity(tableName = "anime_favorites")
@TypeConverters(Converter::class)
data class AnimeFavoriteEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val russian: String,
    val image: ImageInfo,
    val kind: AnimeKind?,
    val score: Float,
    val status: AnimeStatus?,
    val episodes: Int,
    val episodesAired: Int,
    val airedOn: String?,
    val releasedOn: String?
)
