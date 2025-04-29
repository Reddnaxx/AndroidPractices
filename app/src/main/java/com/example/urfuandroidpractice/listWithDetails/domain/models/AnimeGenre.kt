package com.example.urfuandroidpractice.listWithDetails.domain.models

import com.google.gson.annotations.SerializedName

data class AnimeGenre(
    val id: Int,
    val name: String,
    val russian: String,
    @SerializedName("entry_type") val entryType: String,
)