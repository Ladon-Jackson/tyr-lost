package com.example.tyrlost.presentation.components.tierListScreen.footer

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tyrlost.presentation.components.common.IconButtonComponent
import com.example.tyrlost.presentation.components.common.Icons
import com.example.tyrlost.presentation.components.common.ImageComponent


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
    Column(
        Modifier.padding(8.dp)
    ) {
        Card(Modifier.padding(4.dp)) {
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
                        modifier = Modifier,
//                            .fillMaxWidth(0.1f), //TODO 0.25 for some reason makes the images overflow at 3 instead of 4. Please don't forget this ugliness,
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

        if(currentImageSelected != null){
            IconButtonComponent(
                onClick = {
                    deleteImage(currentImageSelected)
                    updateImageSelected(currentImageSelected)
                },
                imageVector = Icons.delete,
                description = "delete"
            )
        } else {
            FooterButtonsComponent(
                addTier = addTier,
                addImages = addImages,
                saveTierAsImage = saveTierAsImage,
            )
        }
    }
}
