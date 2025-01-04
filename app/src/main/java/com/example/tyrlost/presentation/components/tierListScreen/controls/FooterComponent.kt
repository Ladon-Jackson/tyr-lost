package com.example.tyrlost.presentation.components.tierListScreen.controls

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tyrlost.R
import com.example.tyrlost.presentation.components.tierListScreen.IconButtonComponent
import com.example.tyrlost.presentation.components.tierListScreen.ImageComponent


@Composable
fun FooterComponent(
    unlistedImages: List<Uri>,
    currentImageSelected: Uri?,
    updateImageSelected: (Uri) -> Unit,
    addTier: () -> Unit,
    addImages: (List<Uri>) -> Unit,
    saveTierAsImage: () -> Unit,
    moveImageToUnlisted: (Uri) -> Unit,
    moveImageToDestinationImage: (Uri, Uri) -> Unit,
    deleteImage: (Uri) -> Unit,
) {
    Column {

        if(currentImageSelected != null){
            IconButtonComponent(
                onClick = {
                    deleteImage(currentImageSelected)
                    updateImageSelected(currentImageSelected)
                },
                drawableId = R.drawable.deleteicon_border,
                description = "delete"
            )
        } else {
            FooterButtonsComponent(
                addTier = addTier,
                addImages = addImages,
                saveTierAsImage = saveTierAsImage,
            )
        }

        LazyRow(
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .background(Color.DarkGray)
                .clickable {
                    if (currentImageSelected != null) {
                        moveImageToUnlisted(currentImageSelected)
                        updateImageSelected(currentImageSelected)
                    }
                }
        ) {
            items(unlistedImages) {
                ImageComponent(
                    modifier = Modifier.width(width = 80.dp),
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
