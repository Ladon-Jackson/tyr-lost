package com.example.tyrlost.presentation.components.tierListScreen.controls

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun AddButtonsComponent(
    addTier: () -> Unit,
    addImages: (List<Uri>) -> Unit,
) {

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { addImages(it) }
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Button(
            onClick = {
                addTier()
            },
            modifier = Modifier
                .weight(1f)
        ) { Text("Add Tier") }
        Button(
            onClick = {
                photoPickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            },
            modifier = Modifier
                .weight(1f)
        ) { Text("Add Image") }
    }
}
