package com.example.tyrlost.presentation.components.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun DialogButtonsComponent(
    onDismiss: () -> Unit,
    onUpdate: () -> Unit,
    onReset: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Button(
            modifier = Modifier
                .weight(1f),
            onClick = {
                onReset()
                onDismiss()
            }
        ) { Text("Cancel") }

        Button(
            modifier = Modifier
                .weight(1f),
            onClick = {
                onUpdate()
                onReset()
                onDismiss()
            }
        ) { Text("Apply") }
    }
}
