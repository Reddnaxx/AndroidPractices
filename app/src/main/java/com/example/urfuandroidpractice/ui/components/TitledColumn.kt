package com.example.urfuandroidpractice.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun TitledColumn(modifier: Modifier = Modifier, title: String, content: @Composable () -> Unit) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .clip(
                    shape = RoundedCornerShape(2.dp)
                )
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(vertical = 4.dp, horizontal = 8.dp)
                .fillMaxWidth()
        )
        Column {
            content()
        }
    }
}