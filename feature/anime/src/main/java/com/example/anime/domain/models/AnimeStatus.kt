package com.example.anime.domain.models

import android.content.Context
import androidx.annotation.StringRes
import com.example.anime.R

enum class AnimeStatus(@StringRes val stringRes: Int) {
    RELEASED(R.string.released),
    ONGOING(R.string.ongoing);

    fun getString(context: Context): String {
        return context.getString(stringRes)
    }
}