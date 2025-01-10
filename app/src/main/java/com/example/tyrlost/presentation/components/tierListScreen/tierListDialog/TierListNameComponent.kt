package com.example.tyrlost.presentation.components.tierListScreen.tierListDialog

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import com.example.tyrlost.presentation.components.common.DialogButtonsComponent


@Composable
fun TierListNameComponent(
    name: String,
    onTextFieldValueChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onUpdate: () -> Unit,
    onReset: () -> Unit,
) {

    Column {

        TextField(
            value = name,
            onValueChange = onTextFieldValueChange,
            label = { Text("Tier Name") }
        )

        DialogButtonsComponent(
            onDismiss = onDismiss,
            onUpdate = onUpdate,
            onReset = onReset
        )
    }
}
