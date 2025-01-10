package com.example.tyrlost.presentation.components.tierListScreen.tierListDialog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tyrlost.presentation.viewModels.TierListDetailsViewModel


@Composable
fun TierListDetailsDialogComponent(
    name: String,
    onDismiss: () -> Unit,
    onUpdate: (String) -> Unit,
    tierListDetailsViewModel: TierListDetailsViewModel = viewModel(),
) {

    val newName: String by tierListDetailsViewModel.text.collectAsStateWithLifecycle()
    tierListDetailsViewModel.updateText(name)

    Dialog(onDismissRequest = onDismiss) {

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
        ) {
            TierListNameComponent(
                name = newName,
                onTextFieldValueChange = tierListDetailsViewModel::updateText,
                onDismiss = onDismiss,
                onUpdate = {onUpdate(newName)},
                onReset = tierListDetailsViewModel::onReset
            )
        }
    }
}
