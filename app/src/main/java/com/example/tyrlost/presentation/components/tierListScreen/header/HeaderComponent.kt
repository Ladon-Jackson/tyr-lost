package com.example.tyrlost.presentation.components.tierListScreen.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tyrlost.R
import com.example.tyrlost.presentation.components.tierListScreen.IconButtonComponent


@Composable
fun HeaderComponent(
    tierListName: String,
    onBack: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButtonComponent(
            drawableId = R.drawable.backicon,
            description = "back",
            onClick = onBack
        )

        Text(
            text = tierListName,
            textAlign = TextAlign.Center
        )

        IconButtonComponent(
            drawableId = R.drawable.editicon,
            description = "back",
            onClick = onBack
        )
    }

}
