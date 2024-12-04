package com.example.tyrlost.presentation.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun MainComponent(
    modifier: Modifier = Modifier,
    navigateToTierList: () -> Unit,
) {

    Button(
        onClick = navigateToTierList,
        modifier = modifier
    ) { Text("Open Tier") }
}
