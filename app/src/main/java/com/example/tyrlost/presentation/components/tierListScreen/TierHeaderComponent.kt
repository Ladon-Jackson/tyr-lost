package com.example.tyrlost.presentation.components.tierListScreen

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign


@Composable
fun TierHeaderComponent(
    modifier: Modifier,
    index: Int,
    name: String,
    color: Color,
    currentImageSelected: Uri?,
    moveImageToTier: (updateIndex: Int, image: Uri) -> Unit,
    updateImageSelected: (image: Uri) -> Unit,
    openTierDialog: (dialogIndex: Int) -> Unit,
) {
    Box(
        modifier = modifier
            .background(color)
            .clickable {
                if (currentImageSelected == null) openTierDialog(index) else {
                    moveImageToTier(index, currentImageSelected)
                    updateImageSelected(currentImageSelected)
                }
            }
    ) {
        Text(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight(align = Alignment.CenterVertically),
            text = name,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )
    }
}
