package com.example.tyrlost.presentation.components.tierListScreen.tierImageDialog

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
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

            FlowRow(modifier = Modifier.weight(5f)) {
                tierModel.images.forEach {
                    ImageComponent(
                        modifier = Modifier
                            .fillMaxWidth(0.2f)
                            .aspectRatio(1f),
                        image = it,
                        isSelected = false
                    )
                }
            }
        }
    }
}

