package com.example.tyrlost.presentation.components.common

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage


@Composable
fun ImageComponent(
    modifier: Modifier,
    image: Uri,
    isSelected: Boolean = false,
    onClick: () -> Unit = {},
) {

    Box(modifier = modifier
        .fillMaxSize()
        .clickable { onClick() }
    ) {
        AsyncImage(
            model = image,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}
