package com.example.tyrlost.presentation.components.tierListScreen.controls

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.tyrlost.R
import com.example.tyrlost.presentation.components.tierListScreen.IconButtonComponent


@Composable
fun FooterButtonsComponent(
    addTier: () -> Unit,
    addImages: (List<Uri>) -> Unit,
    saveTierAsImage: () -> Unit,
) {

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { addImages(it) }
    )

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        IconButtonComponent(
            onClick = { addTier() },
            drawableId = R.drawable.newtiericon,
            description = "Add tier",
        )

        IconButtonComponent(
            onClick = {
                photoPickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            },
            drawableId = R.drawable.newimageicon,
            description = "Add image",
        )

        IconButtonComponent(
            onClick = { saveTierAsImage() },
            drawableId = R.drawable.saveicon,
            description = "Save as image",
        )
    }
}
