package com.example.urfuandroidpractice.listWithDetails.domain.entity

import android.content.Context
import androidx.annotation.StringRes
import com.example.urfuandroidpractice.R

enum class AnimeKind(@StringRes val stringRes: Int) {
    TV(R.string.tv),
    MOVIE(R.string.movie);

    fun getString(context: Context): String {
        return context.getString(stringRes)
    }
}