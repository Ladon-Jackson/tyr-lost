package com.example.tyrlost.presentation.components.tierList

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
fun TierHeaderComponent(
    name: String,
    index: Int,
    currentImageSelected: Int?,
    updateTiers: (updateIndex: Int, image: Int) -> Unit,
    selectImage: (image: Int) -> Unit,
    openDialog: (dialogIndex: Int) -> Unit,
) {

    Box(modifier = Modifier
        .width(80.dp)
        .clickable {
            if(currentImageSelected == null) openDialog(index) else {
                updateTiers(index, currentImageSelected)
                selectImage(currentImageSelected)
            }
        }
    ){
        Text(
            modifier = Modifier
                .height(80.dp)
                .width(80.dp)
                .wrapContentHeight(align = Alignment.CenterVertically),
            text = name,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}
