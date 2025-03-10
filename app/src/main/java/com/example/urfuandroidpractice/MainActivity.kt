package com.example.urfuandroidpractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.urfuandroidpractice.listWithDetails.presentation.screens.AnimeListScreen
import com.example.urfuandroidpractice.ui.theme.UrFUAndroidPracticeTheme
import com.github.terrakok.modo.Modo.rememberRootScreen
import com.github.terrakok.modo.stack.DefaultStackScreen
import com.github.terrakok.modo.stack.StackNavModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val rootScreen = rememberRootScreen {
                DefaultStackScreen(
                    StackNavModel(AnimeListScreen())
                )
            }

            UrFUAndroidPracticeTheme {
                Surface {
                    rootScreen.Content(
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}