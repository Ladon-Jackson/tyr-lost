package com.example.tyrlost.presentation.components.tierListScreen

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun TierHeaderComponent(
    index: Int,
    name: String,
    color: Color,
    currentImageSelected: Uri?,
    moveImageToTier: (updateIndex: Int, image: Uri) -> Unit,
    updateImageSelected: (image: Uri) -> Unit,
    openTierDialog: (dialogIndex: Int) -> Unit,
) {
    Box(
        modifier = Modifier
            .background(color)
            .fillMaxHeight()
            .width(80.dp)
            .clickable {
                if (currentImageSelected == null) openTierDialog(index) else {
                    moveImageToTier(index, currentImageSelected)
                    updateImageSelected(currentImageSelected)
                }
            }
    ) {
        Text(
            modifier = Modifier
                .height(80.dp)
                .width(80.dp)
                .wrapContentHeight(align = Alignment.CenterVertically),
            text = name,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )
    }
}
