package com.example.tyrlost.presentation.components.tierListScreen

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
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

    val tileSize = LocalConfiguration.current.screenWidthDp / 5

    Row(
        modifier = Modifier
            .defaultMinSize(minHeight = tileSize.dp)
            .fillMaxWidth()
            .background(Color.Gray)
            .clickable {
                if (currentImageSelected != null) {
                    moveImageToTier(index, currentImageSelected)
                    updateImageSelected(currentImageSelected)
                }
            }
            .border(1.dp, Color.Black)
    ) {

        //TODO ugly way of doing this probs a nicer way to set height but maxHeight doesn't work for some reason
        val tierHeightInTiles: Int =
            if(tierModel.images.isEmpty()) 1 else ((tierModel.images.size-1) / 4) + 1

        val tierHeight = (tileSize.dp) * tierHeightInTiles

        TierHeaderComponent(
            modifier = Modifier
                .width(width = tileSize.dp)
                .height(height = tierHeight),
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
                    modifier = Modifier.size(tileSize.dp),
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
