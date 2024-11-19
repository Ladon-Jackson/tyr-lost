package com.example.tyrlost.presentation.components

import android.content.ClipData
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tyrlost.presentation.models.testTiers
import com.example.tyrlost.ui.theme.TyrlostTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemComponent(image: Int) {

    Box(
        modifier = Modifier
            .padding(1.dp)
            .width(80.dp)
            .height(80.dp)
            .dragAndDropSource {
                detectTapGestures(
                    onLongPress = { offset ->
                       startTransfer(
                           transferData = DragAndDropTransferData(
                               clipData = ClipData.newPlainText(
                                   "image",
                                   image.toString()
                               )
                           )
                       )
                    }
                )
            }
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null
        )
    }
}

@Preview(showBackground = false)
@Composable
private fun ItemComponentPreview() {
    TyrlostTheme {
        ItemComponent(testTiers[0].items[0])
    }
}