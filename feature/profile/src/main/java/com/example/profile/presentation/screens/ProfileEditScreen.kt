package com.example.profile.presentation.screens

import TimePickerField
import android.Manifest
import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.example.profile.domain.models.UserProfile
import com.example.profile.presentation.viewModel.ProfileViewModel
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.LocalStackNavigation
import com.github.terrakok.modo.stack.back
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Parcelize
class ProfileEditScreen(
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content(modifier: Modifier) {
        val navigation = LocalStackNavigation.current

        val viewModel = koinViewModel<ProfileViewModel> { parametersOf(navigation) }
        val state by viewModel.profile.collectAsState()

        var isValid by remember { mutableStateOf(true) }

        var fullName by remember(state.fullName) { mutableStateOf(state.fullName) }
        var resumeUrl by remember(state.resumeUrl) { mutableStateOf(state.resumeUrl) }
        var avatarUri by remember(state.avatarUri) { mutableStateOf(state.avatarUri) }

        val context = LocalContext.current

        val pickImageLauncher = rememberLauncherForActivityResult(
            ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            uri?.let { avatarUri = it.toString() }
        }
        val takePhotoLauncher = rememberLauncherForActivityResult(
            ActivityResultContracts.TakePicturePreview()
        ) { bmp: Bitmap? ->
            bmp?.let {
                val uri = viewModel.storeBitmapToCache(context, it)
                avatarUri = uri.toString()
            }
        }

        val permissionLauncher = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            it.entries.forEach { entry ->
                if (entry.value) {
                    when (entry.key) {
                        Manifest.permission.CAMERA -> takePhotoLauncher.launch(null)
                        Manifest.permission.READ_EXTERNAL_STORAGE -> pickImageLauncher.launch("image/*")
                    }
                }
            }
        }

        var showDialog by remember { mutableStateOf(false) }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Редактировать профиль") },
                    navigationIcon = {
                        IconButton(onClick = {
                            navigation.back()
                        }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            viewModel.saveProfile(
                                UserProfile(
                                    fullName = fullName,
                                    avatarUri = avatarUri,
                                    resumeUrl = resumeUrl,
                                )
                            )
                            navigation.back()
                        }) {
                            Icon(Icons.Default.Check, contentDescription = "Готово")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors()
                )
            },
            content = { innerPadding ->
                Column(
                    modifier = modifier
                        .padding(innerPadding)
                        .padding(16.dp)
                        .verticalScroll(ScrollState(0)),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray)
                            .clickable { showDialog = true },
                        contentAlignment = Alignment.Center
                    ) {
                        if (avatarUri.isNotBlank()) {
                            Image(
                                painter = rememberAsyncImagePainter(avatarUri),
                                contentDescription = "Аватар",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                        } else {
                            Icon(
                                Icons.Default.AccountCircle,
                                contentDescription = "Пустой аватар",
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                        }
                    }

                    if (showDialog) {
                        AlertDialog(
                            onDismissRequest = { showDialog = false },
                            title = { Text("Выберите источник изображения") },
                            confirmButton = {
                                TextButton(onClick = { showDialog = false }) {
                                    Text("Отмена")
                                }
                            },
                            text = {
                                Column {
                                    Text(
                                        "Галерея",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable {
                                                permissionLauncher.launch(
                                                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                                                )
                                                showDialog = false
                                            }
                                            .padding(16.dp)
                                    )
                                    Text(
                                        "Камера",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable {
                                                permissionLauncher.launch(
                                                    arrayOf(Manifest.permission.CAMERA)
                                                )
                                                showDialog = false
                                            }
                                            .padding(16.dp)
                                    )
                                }
                            }
                        )
                    }

                    OutlinedTextField(
                        value = fullName,
                        onValueChange = { fullName = it },
                        label = { Text("ФИО") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = resumeUrl,
                        onValueChange = { resumeUrl = it },
                        label = { Text("Ссылка на резюме (PDF)") },
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 5
                    )
                    TimePickerField(
                        onTimeChange = { h, m ->
                            viewModel.saveNotificationTime(
                                context = context,
                                hour = h,
                                minute = m
                            )
                        },
                        onValidationChange = {
                            isValid = it
                        }
                    )

                    Spacer(Modifier.height(16.dp))

                    Button(
                        onClick = {
                            viewModel.saveProfile(
                                UserProfile(
                                    fullName = fullName,
                                    avatarUri = avatarUri,
                                    resumeUrl = resumeUrl,
                                )
                            )
                            viewModel.scheduleNotification(context = context)
                            navigation.back()
                        },
                        modifier = Modifier.align(Alignment.End),
                        enabled = isValid
                    ) {
                        Text("Готово")
                    }
                }
            }
        )
    }
}
