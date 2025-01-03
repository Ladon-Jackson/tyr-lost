package com.example.tyrlost.presentation.components.tierListScreen.tierDialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun TierDetailsComponent(
    name: String,
    color: Color,
    onTextFieldValueChange: (String) -> Unit,
    onOpenColors: () -> Unit,
    onDelete: () -> Unit,
    onDismiss: () -> Unit,
    onUpdate: () -> Unit,
    onReset: () -> Unit,
) {

    Column {
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            modifier = Modifier
                .align(Alignment.End),
            onClick = onDelete
        ) { Text("DELETE") }

        TextField(
            value = name,
            onValueChange = onTextFieldValueChange,
            label = { Text("Tier Name") }
        )

        Row(Modifier.fillMaxWidth()) {
            Text(
                text = "Color",
                modifier = Modifier
                    .weight(0.5f)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.Center
            )
            Box(
                Modifier
                    .weight(1f)
                    .background(color = color)
                    .height(30.dp)
                    .fillMaxWidth()
                    .clickable(onClick = onOpenColors)
            )
        }

        TierDialogButtonsComponent(
            onDismiss = onDismiss,
            onUpdate = onUpdate,
            onReset = onReset
        )
    }
}
