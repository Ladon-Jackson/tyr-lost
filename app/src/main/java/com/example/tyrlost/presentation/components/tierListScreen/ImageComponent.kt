package com.example.tyrlost.presentation.components.tierListScreen

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage


@Composable
fun ImageComponent(
    modifier: Modifier,
    image: Uri,
    isSelected: Boolean, onClick: () -> Unit,
) {

    val borderDp: Dp = if(isSelected) 5.dp else 0.dp

    Box(
        modifier = modifier
            .border(width = borderDp, color = Color.Black)
            .clickable {
                onClick()
            }
    ) {
        AsyncImage(
            model = image,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}
