package com.example.tyrlost.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ItemComponent(image: Int, onClick: () -> Unit) {

    Box(
        modifier = Modifier
            .clickable{onClick()}
            .padding(1.dp)
            .width(80.dp)
            .height(80.dp)
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null
        )
    }
}
