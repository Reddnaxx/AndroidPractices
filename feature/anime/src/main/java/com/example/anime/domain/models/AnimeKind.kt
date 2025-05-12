package com.example.anime.domain.models

import android.content.Context
import androidx.annotation.StringRes
import com.example.anime.R

enum class AnimeKind(@StringRes val stringRes: Int) {
    TV(R.string.tv),
    MOVIE(R.string.movie);

    fun getString(context: Context): String {
        return context.getString(stringRes)
    }
}