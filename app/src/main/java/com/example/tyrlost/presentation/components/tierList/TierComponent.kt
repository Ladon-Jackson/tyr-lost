package com.example.tyrlost.presentation.components.tierList

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tyrlost.presentation.components.ImageComponent
import com.example.tyrlost.presentation.models.TierModel


@Composable
fun TierComponent(
    tierModel: TierModel,
    index: Int,
    currentImageSelected: Uri?,
    updateImageSelected: (image: Uri) -> Unit,
    openTierDialog: (dialogIndex: Int) -> Unit,
    moveImageToTier: (updateIndex: Int, image: Uri) -> Unit,
    moveImageToDestinationImage: (Uri, Uri) -> Unit,
) {

    Row(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
            .padding(1.dp)
            .background(tierModel.color)
            .clickable {
                if (currentImageSelected != null) {
                    moveImageToTier(index, currentImageSelected)
                    updateImageSelected(currentImageSelected)
                }
            }
    ) {
        TierHeaderComponent(
            name = tierModel.name,
            index = index,
            currentImageSelected = currentImageSelected,
            moveImageToTier = moveImageToTier,
            updateImageSelected = updateImageSelected,
            openTierDialog = openTierDialog
        )

        LazyRow {

            items(tierModel.images) {
                ImageComponent(
                    image = it,
                    isSelected = it == currentImageSelected
                ) {
                    if (!(currentImageSelected == null || currentImageSelected == it)) {
                        moveImageToDestinationImage(currentImageSelected, it)
                    }
                    updateImageSelected(it)
                }
            }
        }
    }
}
