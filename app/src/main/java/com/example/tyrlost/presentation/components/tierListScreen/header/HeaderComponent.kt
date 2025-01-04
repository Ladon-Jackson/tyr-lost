package com.example.tyrlost.presentation.components.tierListScreen.header

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun HeaderComponent(
    onBack: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Button(onClick = onBack) {
            Text("back")
        }
    }
}
