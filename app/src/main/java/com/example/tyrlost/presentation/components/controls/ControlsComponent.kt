package com.example.tyrlost.presentation.components.controls

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    addImages: (List<Uri>) -> Unit,
    moveImageToUnlisted: (Uri) -> Unit,
    moveImageToDestinationImage: (Uri, Uri) -> Unit,
) {
    Column {
        AddButtonsComponent(
            nextTierIndex = nextTierIndex,
            addTier = addTier,
            openDialog = openTierDialog,
            addImages = addImages
        )

        LazyRow(
            modifier = Modifier
                .padding(5.dp)
                .height(80.dp)
                .fillMaxWidth()
                .clickable {
                    if (currentImageSelected != null) moveImageToUnlisted(currentImageSelected)
                }
        ) {
            items(unlistedImages) {
                ImageComponent(
                    image = it,
                    isSelected = it == currentImageSelected,
                    onClick = {
                        if (!(currentImageSelected == null || currentImageSelected == it)) {
                            moveImageToDestinationImage(currentImageSelected, it)
                        }
                        updateImageSelected(it)
                    }
                )
            }
        }
    }
}
