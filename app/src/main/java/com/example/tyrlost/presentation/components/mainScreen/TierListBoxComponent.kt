package com.example.tyrlost.presentation.components.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tyrlost.presentation.components.common.IconButtonComponent
import com.example.tyrlost.presentation.components.common.Icons


@Composable
fun TierListBoxComponent(
    modifier: Modifier = Modifier,
    deleteTier: () -> Unit,
    text: String
) {

    Card(
        modifier = modifier
            .padding(4.dp)
    ){
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = text,
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            IconButtonComponent(
                onClick = deleteTier,
                imageVector = Icons.delete,
                description = "delete",
                modifier = Modifier.align(Alignment.Top)
            )

        }
    }
}
