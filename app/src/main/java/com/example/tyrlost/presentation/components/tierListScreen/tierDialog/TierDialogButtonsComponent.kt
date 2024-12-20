package com.example.tyrlost.presentation.components.tierListScreen.tierDialog

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun TierDialogButtonsComponent(
    onDismiss: () -> Unit,
    onUpdate: () -> Unit,
    resetText: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingValues(3.dp))
    ) {
        Button(
            modifier = Modifier
                .weight(1f)
                .padding(PaddingValues(1.dp)),
            onClick = {
                resetText()
                onDismiss()
            }
        ) { Text("Cancel") }

        Button(
            modifier = Modifier
                .weight(1f)
                .padding(PaddingValues(1.dp)),
            onClick = {
                onUpdate()
                resetText()
                onDismiss()
            }
        ) { Text("Apply") }
    }
}
