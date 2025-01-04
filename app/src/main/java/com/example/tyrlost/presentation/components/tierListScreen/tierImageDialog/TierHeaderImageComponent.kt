package com.example.tyrlost.presentation.components.tierListScreen.tierImageDialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp


@Composable
fun TierHeaderImageComponent(
    name: String,
    color: Color,
    tileSize: Dp,
) {
    Box(
        modifier = Modifier.background(color)
    ) {
        Text(
            modifier = Modifier
                .wrapContentHeight(align = Alignment.CenterVertically)
                .size(tileSize),
            text = name,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )
    }
}
