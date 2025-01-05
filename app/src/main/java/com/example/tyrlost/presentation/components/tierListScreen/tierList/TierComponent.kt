package com.example.tyrlost.presentation.components.tierListScreen.tierList

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.tyrlost.models.TierModel
import com.example.tyrlost.presentation.components.common.ImageComponent


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

    Card(
        shape = RoundedCornerShape(
            topStart = 16.dp,
            bottomStart = 16.dp,
            topEnd = 0.dp,
            bottomEnd = 0.dp
        ),
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth()
            .clickable {
                if (currentImageSelected != null) {
                    moveImageToTier(index, currentImageSelected)
                    updateImageSelected(currentImageSelected)
                }
            }
    ) {
        Row {
            TierHeaderComponent(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f),
                index = index,
                name = tierModel.name,
                color = tierModel.color,
                currentImageSelected = currentImageSelected,
                moveImageToTier = moveImageToTier,
                updateImageSelected = updateImageSelected,
                openTierDialog = openTierDialog
            )

            FlowRow(modifier = Modifier.weight(5f)) {
                tierModel.images.forEach {
                    ImageComponent(
                        modifier = Modifier
                            .fillMaxWidth(0.19999f) //TODO 0.25 for some reason makes the images overflow at 3 instead of 4. Please don't forget this ugliness
                            .aspectRatio(1f)
                            .padding(1.dp),
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
}
