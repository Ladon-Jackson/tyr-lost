package com.example.tyrlost.presentation.components.tierList

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tyrlost.presentation.models.TierModel


@Composable
fun TierComponent(
    tierModel: TierModel,
    index: Int,
    currentImageSelected: Int?,
    updateTiers: (updateIndex: Int, image: Int) -> Unit,
    selectImage: (image: Int) -> Unit,
    openDialog: (dialogIndex: Int) -> Unit,
) {

    Row(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
            .padding(1.dp)
            .background(tierModel.color)
    ) {
        TierHeaderComponent(
            name = tierModel.name,
            index = index,
            currentImageSelected = currentImageSelected,
            updateTiers = updateTiers,
            selectImage = selectImage,
            openDialog = openDialog
        )

        LazyRow() {

            items(tierModel.images) {
                ImageComponent(
                    image = it,
                    isSelected = it == currentImageSelected
                ) {
                    selectImage(it)
                }
            }
        }
    }
}
