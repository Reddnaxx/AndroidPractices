package com.example.urfuandroidpractice.listWithDetails.domain.entity

import android.content.Context
import androidx.annotation.StringRes
import com.example.urfuandroidpractice.R

enum class AnimeStatus(@StringRes val stringRes: Int) {
    RELEASED(R.string.released),
    ONGOING(R.string.ongoing);

    fun getString(context: Context): String {
        return context.getString(stringRes)
    }
}