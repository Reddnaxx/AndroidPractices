package com.example.anime.data.repository

import com.example.anime.data.local.AnimeFavoriteDao
import com.example.anime.data.local.AnimeFavoriteEntity
import com.example.anime.domain.models.AnimeShortModel
import com.example.anime.domain.models.AnimeStatus
import com.example.anime.domain.repository.IFavoritesRepository

class FavoritesRepository(
    private val dao: AnimeFavoriteDao
) : IFavoritesRepository {

    override suspend fun getFavorites(): List<AnimeShortModel> {
        return dao.getFavorites().map {
            AnimeShortModel(
                id = it.id,
                name = it.name,
                russian = it.russian,
                image = it.image,
                kind = it.kind,
                score = it.score,
                status = it.status ?: AnimeStatus.RELEASED,
                episodes = it.episodes,
                episodesAired = it.episodesAired,
                airedOn = it.airedOn,
                releasedOn = it.releasedOn
            )
        }
    }

    override suspend fun addToFavorites(anime: AnimeShortModel) {
        val isFavorite = dao.getFavorites().any { it.id == anime.id }
        if (isFavorite) {
            return dao.deleteAnime(anime.id)
        } else {
            val animeEntity = AnimeFavoriteEntity(
                id = anime.id,
                name = anime.name,
                russian = anime.russian,
                image = anime.image,
                kind = anime.kind,
                score = anime.score,
                status = anime.status,
                episodes = anime.episodes,
                episodesAired = anime.episodesAired,
                airedOn = anime.airedOn,
                releasedOn = anime.releasedOn
            )
            return dao.insertAnime(animeEntity)
        }
    }
}