package com.example.tyrlost.presentation.components.controls

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tyrlost.presentation.components.ImageComponent


@Composable
fun ControlsComponent(
    nextTierIndex: Int,
    unlistedImages: List<Uri>,
    currentImageSelected: Uri?,
    updateImageSelected: (Uri) -> Unit,
    addTier: () -> Unit,
    openTierDialog: (Int) -> Unit,
    addImages: (List<Uri>) -> Unit
) {
    Column {
        LazyRow(
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .background(Color.Gray)
        ) {
            items(unlistedImages) {
                ImageComponent(
                    image = it,
                    isSelected = it == currentImageSelected,
                    onClick = {
                        updateImageSelected(it)
                    }
                )
            }
        }

        AddButtonsComponent(
            nextTierIndex = nextTierIndex,
            addTier = addTier,
            openDialog = openTierDialog,
            addImages = addImages
        )
    }
}