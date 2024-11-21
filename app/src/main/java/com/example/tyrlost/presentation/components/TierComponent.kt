package com.example.tyrlost.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tyrlost.presentation.models.TierModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TierComponent(tierModel: TierModel, index: Int, updateTiers: (image: Int, updateIndex: Int) -> Unit) {

    LazyRow(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
            .padding(1.dp)
            .background(tierModel.color)
    ) {
        stickyHeader {
            Text(
                modifier = Modifier
                    .height(80.dp)
                    .width(80.dp)
                    .wrapContentHeight(align = Alignment.CenterVertically),
                text = tierModel.name,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
        items(tierModel.images) {
            ItemComponent(it, {updateTiers(it, index + 1)})
        }
    }
}
