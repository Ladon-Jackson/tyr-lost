package com.example.tyrlost.presentation.components.tierListScreen

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tyrlost.models.TierModel


@OptIn(ExperimentalLayoutApi::class)
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
            .defaultMinSize(minHeight = 80.dp)
//            .height(80.dp)
            .fillMaxWidth()
            .padding(1.dp)
            .background(Color.Gray)
            .clickable {
                if (currentImageSelected != null) {
                    moveImageToTier(index, currentImageSelected)
                    updateImageSelected(currentImageSelected)
                }
            }
    ) {
        TierHeaderComponent(
            index = index,
            name = tierModel.name,
            color = tierModel.color,
            currentImageSelected = currentImageSelected,
            moveImageToTier = moveImageToTier,
            updateImageSelected = updateImageSelected,
            openTierDialog = openTierDialog
        )

        FlowRow {
            tierModel.images.forEach {
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
