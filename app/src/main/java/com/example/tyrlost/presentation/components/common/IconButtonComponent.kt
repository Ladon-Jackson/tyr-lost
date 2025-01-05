package com.example.tyrlost.presentation.components.common

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp


@Composable
fun IconButtonComponent(
    imageVector: ImageVector,
    description: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val iconSize = LocalConfiguration.current.screenWidthDp / 6

    FloatingActionButton(
        onClick = onClick
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = description,
            modifier = modifier
                .width(iconSize.dp)
                .aspectRatio(1f)
        )
    }
}
