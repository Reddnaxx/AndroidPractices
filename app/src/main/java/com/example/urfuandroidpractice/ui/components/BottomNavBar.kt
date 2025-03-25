package com.example.urfuandroidpractice.ui.components

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.urfuandroidpractice.R
import com.example.urfuandroidpractice.Screens
import com.github.terrakok.modo.Screen

@Composable
fun BottomNavBar(
    currentScreen: Screen,
    onTabClick: (Screen) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
    ) {
        val tabs = MainTabs.entries.toTypedArray()

        tabs.map {
            NavigationBarItem(
                icon = { Icon(it.icon, contentDescription = null) },
                label = { Text(stringResource(it.title)) },
                selected = currentScreen == it.screen,
                onClick = {
                    onTabClick(it.screen)
                }
            )
        }
    }
}

enum class MainTabs(
    val icon: ImageVector,
    @StringRes val title: Int,
    val screen: Screen,
) {
    HOME(Icons.Filled.Home, R.string.pages_home, Screens.homeScreen),
    LIST(Icons.AutoMirrored.Filled.List, R.string.pages_list, Screens.listScreen),
}