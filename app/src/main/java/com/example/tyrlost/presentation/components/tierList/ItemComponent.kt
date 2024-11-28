package com.example.tyrlost.presentation.components.tierList

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun ItemComponent(image: Int, isSelected: Boolean, onClick: () -> Unit) {

    val borderDp = if(isSelected) 5.dp else 1.dp

    Box(
        modifier = Modifier
            .clickable{onClick()}
            .padding(1.dp)
            .width(80.dp)
            .height(80.dp)
            .border(BorderStroke(borderDp, Color.Black))
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null
        )
    }
}
