package com.example.app.data

import android.content.Context
import com.example.urfuandroidpractice.listWithDetails.domain.models.UserProfile
import com.example.urfuandroidpractice.listWithDetails.domain.repository.IProfileRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class ProfileRepository(
    context: Context,
) : IProfileRepository {

    companion object {
        private const val FILE_NAME = "user_profile.json"
    }

    private val json = Json { prettyPrint = true }
    private val file = File(context.filesDir, FILE_NAME)

    private val _profileFlow = MutableStateFlow(UserProfile())
    override fun getProfileFlow(): StateFlow<UserProfile> = _profileFlow.asStateFlow()

    init {
        CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
            _profileFlow.value = loadFromFile()
        }
    }

    override suspend fun saveProfile(profile: UserProfile) {
        withContext(Dispatchers.IO) {
            val text = json.encodeToString(profile)
            file.writeText(text)
        }
        _profileFlow.value = profile
    }

    private suspend fun loadFromFile(): UserProfile = withContext(Dispatchers.IO) {
        if (!file.exists()) return@withContext UserProfile()
        val content = file.readText()
        return@withContext json.decodeFromString(content)
    }
}
