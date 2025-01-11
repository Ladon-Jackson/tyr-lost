package com.example.tyrlost.presentation.components.tierListScreen.header

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.example.tyrlost.presentation.components.common.IconButtonComponent
import com.example.tyrlost.presentation.components.common.Icons


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderComponent(
    tierListName: String,
    onBack: () -> Unit,
    onEdit: () -> Unit,
) {

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = tierListName,
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            IconButtonComponent(
                imageVector = Icons.back,
                description = "back",
                onClick = onBack
            )
        },
        actions = {
            IconButtonComponent(
                imageVector = Icons.edit,
                description = "change name",
                onClick = onEdit
            )
        }
    )
}
