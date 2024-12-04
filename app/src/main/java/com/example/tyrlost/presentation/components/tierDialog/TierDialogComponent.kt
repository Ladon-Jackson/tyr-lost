package com.example.tyrlost.presentation.components.tierDialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tyrlost.presentation.viewModels.TierNameFieldViewModel
import com.example.tyrlost.ui.theme.redTier


@Composable
fun TierDialogComponent(
    index: Int,
    name: String,
    onDismiss: () -> Unit,
    onRename: (Int, String) -> Unit,
    onDelete: (Int) -> Unit,
    tierNameFieldViewModel: TierNameFieldViewModel = viewModel(),
) {

    val text: String by tierNameFieldViewModel.text.collectAsStateWithLifecycle()
    tierNameFieldViewModel.updateText(name)

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
        ) {

            Column {
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = redTier),
                    modifier = Modifier
                        .padding(PaddingValues(4.dp))
                        .align(Alignment.End),
                    onClick = {
                        onDelete(index)
                        tierNameFieldViewModel.resetText()
                        onDismiss()
                    }
                ) { Text("DELETE") }

                TextField(
                    value = text,
                    onValueChange = tierNameFieldViewModel::updateText,
                    label = { Text("Tier Name") }
                )

                TierDialogButtonsComponent(
                    index = index,
                    newName = text,
                    onDismiss = onDismiss,
                    onRename = onRename,
                    resetText = tierNameFieldViewModel::resetText
                )
            }
        }
    }
}
