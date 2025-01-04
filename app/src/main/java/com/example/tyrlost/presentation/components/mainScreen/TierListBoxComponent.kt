package com.example.tyrlost.presentation.components.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tyrlost.R
import com.example.tyrlost.presentation.components.tierListScreen.IconButtonComponent


@Composable
fun TierListBoxComponent(
    modifier: Modifier = Modifier,
    deleteTier: () -> Unit,
    text: String
) {
    Column(
        modifier = modifier
            .height(100.dp)
            .background(Color.White)
            .border(1.dp, Color.Black)
    ) {

        IconButtonComponent(
            onClick = deleteTier,
            drawableId = R.drawable.deleteicon,
            description = "delete",
            modifier = Modifier.align(Alignment.End),
        )

        Text(
            text = text,
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}
