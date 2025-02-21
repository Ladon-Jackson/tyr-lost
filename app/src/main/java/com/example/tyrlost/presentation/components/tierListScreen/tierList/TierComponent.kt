package com.example.tyrlost.presentation.components.tierListScreen.tierList

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.tyrlost.models.TierModel
import com.example.tyrlost.presentation.components.common.ImageComponent
import com.example.tyrlost.ui.theme.tierColor1
import java.io.File


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TierComponent(
    tierModel: TierModel,
    index: Int,
    currentImageSelected: Uri?,
    updateImageSelected: (Uri) -> Unit = {uri -> },
    openTierDialog: (Int) -> Unit = {int -> },
    moveImageToTier: (Int, Uri) -> Unit = {int, uri -> },
    moveImageToDestinationImage: (Uri, Uri) -> Unit = {uri, uri2 ->},
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

            val rowHeight: Int = ((tierModel.images.size-1)/5)+1
            val rowRatio: Float = 1f/rowHeight

            TierHeaderComponent(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(rowRatio),
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
                            .fillMaxWidth(0.2f)
                            .fillMaxHeight()
                            .aspectRatio(1f),
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


@Composable
@Preview
fun view() {

    val file = File("", "").toUri()
    val tlm = TierModel("S", tierColor1, listOf(file, file, file, file, file, file))

    Box(Modifier.height(100.dp)) {
        TierComponent(
            tlm,
            1,
            null,
        )
    }
}
