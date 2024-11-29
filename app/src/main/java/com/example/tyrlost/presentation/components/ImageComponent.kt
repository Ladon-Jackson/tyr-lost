package com.example.tyrlost.presentation.components

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage


@Composable
fun ImageComponent(image: Uri, isSelected: Boolean, onClick: () -> Unit) {

    val borderDp: Dp = if(isSelected) 5.dp else 1.dp

    Box(
        modifier = Modifier
            .padding(1.dp)
            .width(80.dp)
            .height(80.dp)
            .border(BorderStroke(width = borderDp, color = Color.Black))
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
