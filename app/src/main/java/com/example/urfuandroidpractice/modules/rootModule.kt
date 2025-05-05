package com.example.urfuandroidpractice.modules

import com.example.app.data.ProfileRepository
import com.example.urfuandroidpractice.listWithDetails.data.repository.AnimeRepository
import com.example.urfuandroidpractice.listWithDetails.data.repository.FavoritesRepository
import com.example.urfuandroidpractice.listWithDetails.domain.repository.IAnimeRepository
import com.example.urfuandroidpractice.listWithDetails.domain.repository.IFavoritesRepository
import com.example.urfuandroidpractice.listWithDetails.domain.repository.IProfileRepository
import com.example.urfuandroidpractice.listWithDetails.presentation.viewModel.AnimeDetailsViewModel
import com.example.urfuandroidpractice.listWithDetails.presentation.viewModel.AnimeFavoritesViewModel
import com.example.urfuandroidpractice.listWithDetails.presentation.viewModel.AnimeListViewModel
import com.example.urfuandroidpractice.listWithDetails.presentation.viewModel.NavigationViewModel
import com.example.urfuandroidpractice.listWithDetails.presentation.viewModel.ProfileViewModel
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