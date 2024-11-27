package com.example.tyrlost.presentation.components.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tyrlost.presentation.TierNameFieldViewModel
import com.example.tyrlost.ui.theme.redTier


@Composable
fun TierDialogButtonsComponent(
    index: Int,
    newName: String,
    onDismiss: () -> Unit,
    onRename: (Int, String) -> Unit,
    resetText: () -> Unit
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
                onRename(index, newName)
                resetText()
                onDismiss()
            }
        ) { Text("Apply") }
    }
}
