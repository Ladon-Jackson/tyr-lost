package com.example.tyrlost.presentation.components.tierListScreen.tierDialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tyrlost.ui.theme.tierColors


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ColorComponent(
    selectedColor: Color,
    selectColor: (Color) -> Unit,
    closeColors: () -> Unit
) {
    FlowRow {
        tierColors.forEach{
            Box(
                modifier = Modifier
                    .background(it)
                    .height(80.dp)
                    .width(80.dp)
                    .clickable {
                        selectColor(it)
                        closeColors()
                    }
            )
        }
    }
}
