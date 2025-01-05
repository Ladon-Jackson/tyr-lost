package com.example.tyrlost.presentation.components.tierListScreen.tierImageDialog

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tyrlost.models.TierListModel
import com.example.tyrlost.presentation.viewModels.TierListViewModel
import dev.shreyaspatil.capturable.capturable
import dev.shreyaspatil.capturable.controller.rememberCaptureController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch


@OptIn(
    ExperimentalComposeUiApi::class,
    ExperimentalComposeApi::class,
    ExperimentalCoroutinesApi::class
)
@Composable
fun SaveTierImageDialogComponent(
    tierListModel: TierListModel,
    onDismiss: () -> Unit,
    tierListViewModel: TierListViewModel =
        hiltViewModel<TierListViewModel, TierListViewModel.TierListViewModelFactory> {it.create(tierListModel.id.toString()) },
) {

    val captureController = rememberCaptureController()

    Dialog(onDismissRequest = onDismiss,) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
        ) {

            val scope = rememberCoroutineScope()

            Button(
                onClick = {
                    scope.launch {
                        val bitmapAsync = captureController.captureAsync()
                        val bitmap: ImageBitmap = bitmapAsync.await()
                        tierListViewModel.saveTierImageLocally(bitmap.asAndroidBitmap())
                        onDismiss()
                    }
                }
            ) {
                Text("Save Image")
            }

            Box(
                modifier = Modifier
                    .capturable(captureController)
                    .padding(8.dp)
                    .border(width = 2.dp, color = Color.Black)
            ) {
                TierListImageComponent(tierListModel)
            }
        }
    }
}
