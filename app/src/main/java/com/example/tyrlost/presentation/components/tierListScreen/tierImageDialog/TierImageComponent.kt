package com.example.tyrlost.presentation.components.tierListScreen.tierImageDialog

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tyrlost.models.TierModel
import com.example.tyrlost.presentation.components.common.ImageComponent


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TierImageComponent(
    tierModel: TierModel,
) {

    Card(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(
            topStart = 8.dp,
            bottomStart = 8.dp,
            topEnd = 0.dp,
            bottomEnd = 0.dp,
        ),
    ) {

        Row {
            TierHeaderImageComponent(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f),
                name = tierModel.name,
                color = tierModel.color,
            )

            FlowRow(modifier = Modifier.weight(6f)) {
                tierModel.images.forEach {
                    ImageComponent(
                        modifier = Modifier
                            .fillMaxWidth(0.1664f) //TODO 0.125 for some reason makes the images overflow at 3 instead of 4. Please don't forget this ugliness
                            .aspectRatio(1f)
                            .padding(1.dp),
                        image = it,
                        isSelected = false
                    )
                }
            }
        }
    }
}

