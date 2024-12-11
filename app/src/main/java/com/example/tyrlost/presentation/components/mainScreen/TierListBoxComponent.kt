package com.example.tyrlost.presentation.components.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tyrlost.presentation.models.TierListModel


@Composable
fun TierListBoxComponent(
    modifier: Modifier = Modifier,
    tierListModel: TierListModel
) {
    Box(
        modifier = modifier
            .padding(10.dp)
            .height(200.dp)
            .background(Color.White)
    ) {
        Text(
            text = tierListModel.name,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        )
    }
}
