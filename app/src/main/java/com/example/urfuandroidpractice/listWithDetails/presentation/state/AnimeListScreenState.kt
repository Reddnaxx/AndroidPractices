package com.example.urfuandroidpractice.listWithDetails.presentation.state

import com.example.urfuandroidpractice.listWithDetails.domain.entity.AnimeShortEntity

interface AnimeListScreenState {
    val items: List<AnimeShortEntity>
    val query: String
    val isEmpty: Boolean
}