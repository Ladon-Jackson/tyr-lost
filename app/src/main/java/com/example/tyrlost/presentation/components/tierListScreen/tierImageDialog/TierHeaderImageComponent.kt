package com.example.tyrlost.presentation.components.tierListScreen.tierImageDialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.em
import com.example.tyrlost.presentation.components.common.ResizingTextComponent


@Composable
fun TierHeaderImageComponent(
    modifier: Modifier,
    name: String,
    color: Color,
) {
    Box(
        modifier = modifier.background(color)
    ) {
        Text(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight(align = Alignment.CenterVertically),
            text = name,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 2.em,
            color = Color.DarkGray
        )
    }
}
