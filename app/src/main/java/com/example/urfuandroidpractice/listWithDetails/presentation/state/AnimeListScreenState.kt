package com.example.urfuandroidpractice.listWithDetails.presentation.state

import com.example.urfuandroidpractice.listWithDetails.domain.entity.AnimeShortEntity
import kotlinx.coroutines.flow.Flow

interface AnimeListScreenState {
    val items: List<AnimeShortEntity>
    val page: Int
    val query: Flow<String>
    val isEmpty: Boolean
    val isLoading: Boolean
    val isLoadingMore: Boolean
    val error: String?
}