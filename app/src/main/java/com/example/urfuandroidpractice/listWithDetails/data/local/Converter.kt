package com.example.urfuandroidpractice.listWithDetails.data.local

import androidx.room.TypeConverter
import com.example.urfuandroidpractice.listWithDetails.domain.models.AnimeKind
import com.example.urfuandroidpractice.listWithDetails.domain.models.AnimeStatus
import com.example.urfuandroidpractice.listWithDetails.domain.models.ImageInfo
import com.google.gson.Gson

class Converter {

    @TypeConverter
    fun fromImage(image: ImageInfo): String {
        return Gson().toJson(image)
    }

    @TypeConverter
    fun toImage(imageString: String): ImageInfo {
        return Gson().fromJson(imageString, ImageInfo::class.java)
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