package com.example.urfuandroidpractice.di

import com.example.anime.data.repository.AnimeRepository
import com.example.anime.data.repository.FavoritesRepository
import com.example.anime.domain.repository.IAnimeRepository
import com.example.anime.domain.repository.IFavoritesRepository
import com.example.anime.presentation.viewModel.AnimeDetailsViewModel
import com.example.anime.presentation.viewModel.AnimeFavoritesViewModel
import com.example.anime.presentation.viewModel.AnimeListViewModel
import com.example.navigation.presentation.viewModel.NavigationViewModel
import com.example.profile.data.repository.ProfileRepository
import com.example.profile.domain.repository.IProfileRepository
import com.example.profile.presentation.viewModel.ProfileViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val rootModule = module {
    single<IAnimeRepository> { AnimeRepository(get()) }
    single<IFavoritesRepository> { FavoritesRepository(get()) }
    single<IProfileRepository> { ProfileRepository(get()) }

    viewModel { AnimeListViewModel(get(), it.get()) }
    viewModel { AnimeDetailsViewModel(get(), it.get(), it.get()) }
    viewModel { AnimeFavoritesViewModel(get(), it.get()) }
    viewModel { NavigationViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
}