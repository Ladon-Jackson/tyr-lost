package com.example.tyrlost.presentation.components.tierListScreen.tierImageDialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.tyrlost.models.TierModel
import com.example.tyrlost.presentation.components.tierListScreen.ImageComponent


@Composable
fun TierImageComponent(
    tierModel: TierModel,
) {

    val tileSize: Dp = 50.dp

    Row(
        modifier = Modifier
            .defaultMinSize(minHeight = tileSize)
            .background(Color.Gray)
            .border(1.dp, Color.Black)
    ) {

        TierHeaderImageComponent(
            name = tierModel.name,
            color = tierModel.color,
            tileSize = tileSize
        )

        Row {
            tierModel.images.forEach {
                ImageComponent(
                    modifier = Modifier.size(tileSize),
                    image = it,
                    isSelected = false
                )
            }
        }
    }
}

