package com.example.tyrlost.presentation.components.controls

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun AddButtonsComponent(
    nextTierIndex: Int,
    addTier: () -> Unit,
    openDialog: (Int) -> Unit,
    addImages: (List<Uri>) -> Unit
) {

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = {images -> addImages(images)}
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PaddingValues(start = 10.dp))

        ) {
            Button(
                onClick = {
                    addTier()
                    openDialog(nextTierIndex)
                },
                modifier = Modifier
                    .padding(PaddingValues(end = 10.dp))
                    .weight(1f)
            ) { Text("Add Tier") }
            Button(
                onClick = {
                    photoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
                modifier = Modifier
                    .padding(PaddingValues(end = 10.dp))
                    .weight(1f)
            ) { Text("Add Image") }
        }
    }
}
