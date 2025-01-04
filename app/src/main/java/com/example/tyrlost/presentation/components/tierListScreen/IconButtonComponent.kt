package com.example.tyrlost.presentation.components.tierListScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tyrlost.R


@Composable
fun IconButtonComponent(
    drawableId: Int,
    description: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val iconSize = LocalConfiguration.current.screenWidthDp / 6

    Image(
        modifier = modifier
            .width(iconSize.dp)
            .border(1.dp, Color.Black)
            .aspectRatio(1f)
            .clickable { onClick() },
        painter = painterResource(id = drawableId),
        contentDescription = description
    )
}
