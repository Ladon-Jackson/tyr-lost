package com.example.tyrlost.presentation.components

import android.content.ClipDescription
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.mimeTypes
import androidx.compose.ui.draganddrop.toAndroidDragEvent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tyrlost.presentation.models.ItemModel
import com.example.tyrlost.presentation.models.TierModel
import com.example.tyrlost.presentation.models.testTiers
import com.example.tyrlost.ui.theme.TyrlostTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TierComponent(tierModel: TierModel, updateTiers: (Int, Int) -> Unit) {

    val dndTarget = remember {
        object: DragAndDropTarget {
            override fun onDrop(event: DragAndDropEvent): Boolean {
                val draggedData = event.toAndroidDragEvent()
                    .clipData.getItemAt(0).text
                updateTiers(draggedData.toString().toInt(), 2)
                return true
            }
        }
    }

    Row(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
            .padding(1.dp)
            .background(tierModel.color)
            .dragAndDropTarget(
                shouldStartDragAndDrop = { event -> event
                    .mimeTypes()
                    .contains(ClipDescription.MIMETYPE_TEXT_PLAIN)
                },
                target = dndTarget
            )
    ) {
        Text(
            text = tierModel.name,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .height(80.dp)
                .width(80.dp)
                .wrapContentHeight(align = Alignment.CenterVertically),
            fontWeight = FontWeight.Bold
        )
        tierModel.items.forEach { image ->
            ItemComponent(image)
        }
    }
}

//@Preview(showBackground = false)
//@Composable
//private fun TierComponentPreview() {
//    TyrlostTheme {
//        TierComponent(testTiers[0])
//    }
//}

