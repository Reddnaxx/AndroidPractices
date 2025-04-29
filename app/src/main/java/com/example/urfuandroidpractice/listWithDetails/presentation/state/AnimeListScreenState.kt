package com.example.urfuandroidpractice.listWithDetails.presentation.state

import com.example.urfuandroidpractice.listWithDetails.domain.models.AnimeGenre
import com.example.urfuandroidpractice.listWithDetails.domain.models.AnimeShortModel
import kotlinx.coroutines.flow.Flow

interface AnimeListScreenState {
    val items: List<AnimeShortModel>
    val page: Int
    val query: Flow<String>
    val genres: List<AnimeGenre>
    val selectedGenre: AnimeGenre?
    val isEmpty: Boolean
    val isLoading: Boolean
    val isLoadingMore: Boolean
    val isError: Boolean
    val error: String?
}