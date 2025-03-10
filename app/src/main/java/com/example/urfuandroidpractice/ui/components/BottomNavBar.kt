package com.example.urfuandroidpractice.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavBar(
    selectedTabPos: Int,
    onTabClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    BottomAppBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        for ((pos, tab) in MainTabs.entries.withIndex()) {
            IconButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    onTabClick(pos)
                },
            ) {
                val contentColor = LocalContentColor.current
                val color by animateColorAsState(
                    contentColor.copy(
                        alpha = if (pos == selectedTabPos) contentColor.alpha else 0.5f
                    ), label = ""
                )
                Icon(
                    rememberVectorPainter(tab.icon),
                    tint = color,
                    contentDescription = tab.title,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}

enum class MainTabs(
    val icon: ImageVector,
    val title: String
) {
    HOME(Icons.Filled.Home, "Главная"),
    LIST(Icons.AutoMirrored.Filled.List, "Список"),
    WATCH(Icons.Filled.PlayArrow, "Смотреть"),
    NOTIFICATIONS(Icons.Filled.Notifications, "Уведомления")
}