package com.example.urfuandroidpractice.listWithDetails.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.example.urfuandroidpractice.listWithDetails.domain.models.UserProfile
import com.example.urfuandroidpractice.listWithDetails.presentation.viewModel.ProfileViewModel
import com.example.urfuandroidpractice.ui.theme.Spacing
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.LocalStackNavigation
import com.github.terrakok.modo.stack.forward
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Parcelize
class ProfileScreen(
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content(modifier: Modifier) {
        val navigation = LocalStackNavigation.current
        val context = LocalContext.current

        val viewModel = koinViewModel<ProfileViewModel> { parametersOf(navigation) }
        val state by viewModel.profile.collectAsState()
        
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    title = { Text("Профиль") },
                    actions = {
                        IconButton(onClick = { navigation.forward(ProfileEditScreen()) }) {
                            Icon(Icons.Default.Edit, contentDescription = "Edit")
                        }
                    }
                )
            }
        ) { paddingValues ->
            ProfileContent(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(Spacing.medium),
                profile = state,
                onDownloadClick = { viewModel.enqueueDownloadRequest(state.resumeUrl, context) }
            )
        }
    }


    @Composable
    private fun ProfileContent(
        modifier: Modifier = Modifier,
        onDownloadClick: () -> Unit,
        profile: UserProfile,
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Avatar
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                if (profile.avatarUri.isNotBlank()) {
                    Image(
                        painter = rememberAsyncImagePainter(profile.avatarUri),
                        contentDescription = "Аватар",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Icon(
                        Icons.Default.AccountCircle,
                        contentDescription = "Пустой аватар",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Spacer(Modifier.height(16.dp))
            Text(
                text = profile.fullName,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.height(12.dp))
            Button(
                enabled = profile.resumeUrl.isNotBlank(),
                onClick = onDownloadClick
            ) {
                Text("Скачать резюме")
            }
        }
    }
}
