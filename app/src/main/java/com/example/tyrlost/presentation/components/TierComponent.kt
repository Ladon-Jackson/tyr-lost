package com.example.tyrlost.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.toAndroidDragEvent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tyrlost.presentation.models.ItemModel
import com.example.tyrlost.presentation.models.TierModel
import com.example.tyrlost.presentation.models.testTiers
import com.example.tyrlost.ui.theme.TyrlostTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TierComponent(tierModel: TierModel) {

    val items = remember {
        mutableStateOf(tierModel.items)
    }

    val dndtarget = remember {
        object: DragAndDropTarget {
            override fun onDrop(event: DragAndDropEvent): Boolean {
                val draggedData = event.toAndroidDragEvent().clipData.getItemAt(0).text.toString()
                items.value += ItemModel(draggedData)
                return true
            }
        }
    }

    Row(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .padding(10.dp)
            .background(tierModel.color)
            .dragAndDropTarget(
                shouldStartDragAndDrop = { event -> true },
                target = dndtarget
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        tierModel.items.map { x ->
            ItemComponent(x)
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun TierComponentPreview() {
    TyrlostTheme {
        TierComponent(testTiers[0])
    }
}

