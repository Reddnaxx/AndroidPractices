package com.example.urfuandroidpractice.listWithDetails.domain.repository

import com.example.urfuandroidpractice.listWithDetails.domain.models.UserProfile
import kotlinx.coroutines.flow.Flow

interface IProfileRepository {
    fun getProfileFlow(): Flow<UserProfile>

    suspend fun saveProfile(profile: UserProfile)
}