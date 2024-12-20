package com.example.tyrlost.presentation.components.tierListScreen.tierDialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tyrlost.presentation.viewModels.TierDetailsViewModel
import com.example.tyrlost.ui.theme.redTier


@OptIn(ExperimentalStdlibApi::class)
@Composable
fun TierDialogComponent(
    index: Int,
    name: String,
    color: Color,
    onDismiss: () -> Unit,
    onRename: (Int, String) -> Unit,
    onColorChange: (Int, Color) -> Unit,
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
                    selectedColor = newColor,
                    selectColor = tierDetailsViewModel::updateColor,
                    closeColors = {tierDetailsViewModel.setColorsOpen(false)}
                )
            } else {
                Column {
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = redTier),
                        modifier = Modifier
                            .padding(PaddingValues(4.dp))
                            .align(Alignment.End),
                        onClick = {
                            onDelete(index)
                            tierDetailsViewModel.reset()
                            onDismiss()
                        }
                    ) { Text("DELETE") }

                    TextField(
                        value = newName,
                        onValueChange = tierDetailsViewModel::updateText,
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
                                .padding(8.dp)
                                .weight(1f)
                                .background(color = newColor)
                                .height(30.dp)
                                .fillMaxWidth()
                                .clickable{tierDetailsViewModel.setColorsOpen(true)}
                        )
                    }

                    TierDialogButtonsComponent(
                        onDismiss = onDismiss,
                        onUpdate = {
                            onRename(index, newName)
                            onColorChange(index, newColor)
                        },
                        resetText = tierDetailsViewModel::reset
                    )
                }
            }
        }
    }
}
