package com.example.profile.domain.repository

import com.example.profile.domain.models.UserProfile
import kotlinx.coroutines.flow.Flow

interface IProfileRepository {
    fun getProfileFlow(): Flow<UserProfile>

    suspend fun saveProfile(profile: UserProfile)
}