package com.example.tyrlost.presentation.components

import android.content.ClipData
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.unit.dp
import com.example.tyrlost.presentation.models.ItemModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemComponent(itemModel: ItemModel) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(80.dp)
            .dragAndDropSource {
                detectTapGestures(
                    onLongPress = {
                        startTransfer(DragAndDropTransferData(
                            ClipData.newPlainText("item name", itemModel.name)
                        ))
                    }
                )
            },
        ) {
        Text(
            text = itemModel.name
        )
    }
}
