package com.example.profile.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    val fullName: String = "",
    val avatarUri: String = "",
    val resumeUrl: String = ""
)