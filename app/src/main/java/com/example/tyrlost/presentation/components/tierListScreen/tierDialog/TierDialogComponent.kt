package com.example.tyrlost.presentation.components.tierListScreen.tierDialog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tyrlost.presentation.viewModels.TierDetailsViewModel


@Composable
fun TierDialogComponent(
    index: Int,
    name: String,
    color: Color,
    onDismiss: () -> Unit,
    onTierUpdate: (Int, String?, Color?) -> Unit,
    onDelete: (Int) -> Unit,
    tierDetailsViewModel: TierDetailsViewModel = viewModel(),
) {

    val newName: String by tierDetailsViewModel.text.collectAsStateWithLifecycle()
    tierDetailsViewModel.updateText(name)

    val newColor: Color by tierDetailsViewModel.color.collectAsStateWithLifecycle()
    tierDetailsViewModel.updateColor(color)

    val colorsOpen: Boolean by tierDetailsViewModel.colorsOpen.collectAsStateWithLifecycle()

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
        ) {
            if(colorsOpen) {
                ColorComponent(
                    selectColor = tierDetailsViewModel::updateColor,
                    closeColors = {tierDetailsViewModel.setColorsOpen(false)}
                )
            } else {
                TierDetailsComponent(
                    name = newName,
                    color = newColor,
                    onTextFieldValueChange = tierDetailsViewModel::updateText,
                    onOpenColors = {tierDetailsViewModel.setColorsOpen(true)},
                    onDelete = {
                        onDelete(index)
                        tierDetailsViewModel.onReset()
                        onDismiss()
                    },
                    onDismiss = onDismiss,
                    onUpdate = {onTierUpdate(index, newName, newColor)},
                    onReset = tierDetailsViewModel::onReset
                )
            }
        }
    }
}
