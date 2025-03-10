package com.example.urfuandroidpractice.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.urfuandroidpractice.R

@Composable
fun RatingBar(
    rating: Float,
    maxRating: Int = 5,
    onRatingChanged: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(2.dp)) {
        for (i in 1..maxRating) {
            IconButton(
                onClick = { onRatingChanged(i.toFloat()) },
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    painterResource(id = if (i <= rating) R.drawable.star else R.drawable.star_outlined),
                    null,
                    modifier = Modifier.size(32.dp),
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun RatingBarPreview() {
    RatingBar(rating = 3.5f, onRatingChanged = {})
}