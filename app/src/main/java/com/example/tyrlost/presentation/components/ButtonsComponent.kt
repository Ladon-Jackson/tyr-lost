package com.example.tyrlost.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun AddButtonsComponent(
    onAddTier: () -> Unit,
    addImage: () -> Unit
) {

    Box(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PaddingValues(start = 10.dp))
                .align(Alignment.BottomCenter)
        ) {
            Button(
                onClick = onAddTier,
                modifier = Modifier
                    .padding(PaddingValues(end = 10.dp))
                    .weight(1f)
            ) { Text("Add Tier") }
            Button(
                onClick = {
                    addImage()
                },
                modifier = Modifier
                    .padding(PaddingValues(end = 10.dp))
                    .weight(1f)
            ) { Text("Add Image") }
        }
    }
}
