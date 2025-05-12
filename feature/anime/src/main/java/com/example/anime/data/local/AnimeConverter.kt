package com.example.anime.data.local

import androidx.room.TypeConverter
import com.example.anime.domain.models.AnimeImageInfo
import com.example.anime.domain.models.AnimeKind
import com.example.anime.domain.models.AnimeStatus
import com.google.gson.Gson

class AnimeConverter {

    @TypeConverter
    fun fromImage(image: AnimeImageInfo): String {
        return Gson().toJson(image)
    }

    @TypeConverter
    fun toImage(imageString: String): AnimeImageInfo {
        return Gson().fromJson(imageString, AnimeImageInfo::class.java)
    }

    @TypeConverter
    fun fromKind(kind: AnimeKind?): String? {
        return kind?.name
    }

    @TypeConverter
    fun toKind(kindString: String?): AnimeKind? {
        return kindString?.let { AnimeKind.valueOf(it) }
    }

    @TypeConverter
    fun fromStatus(status: AnimeStatus?): String? {
        return status?.name
    }

    @TypeConverter
    fun toStatus(statusString: String?): AnimeStatus? {
        return statusString?.let { AnimeStatus.valueOf(it) }
    }
}