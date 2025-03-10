package com.example.urfuandroidpractice.listWithDetails.domain.entity

data class AnimeShortEntity(
    val id: Int,
    val name: String,
    val russian: String,
    val image: ImageInfo,
    val kind: AnimeKind,
    val score: Float,
    val status: AnimeStatus,
    val episodes: Int,
    val episodesAired: Int,
    val airedOn: String,
    val releasedOn: String?
)
