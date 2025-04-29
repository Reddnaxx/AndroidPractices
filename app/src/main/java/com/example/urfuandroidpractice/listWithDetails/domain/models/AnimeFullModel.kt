package com.example.urfuandroidpractice.listWithDetails.domain.models

import com.google.gson.annotations.SerializedName

data class AnimeFullModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("russian") val russian: String,
    @SerializedName("image") val image: ImageInfo,
    @SerializedName("kind") val kind: AnimeKind?,
    @SerializedName("score") val score: Float,
    @SerializedName("status") val status: AnimeStatus?,
    @SerializedName("episodes") val episodes: Int,
    @SerializedName("episodes_aired") val episodesAired: Int,
    @SerializedName("aired_on") val airedOn: String?,
    @SerializedName("released_on") val releasedOn: String?,
    @SerializedName("rating") val rating: String,
    @SerializedName("duration") val duration: Int,
    @SerializedName("description") val description: String?,
    @SerializedName("genres") val genres: List<AnimeGenre>
)