package com.example.tyrlost.presentation.components.tierListScreen.tierImageDialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toUri
import com.example.tyrlost.models.TierListModel
import com.example.tyrlost.models.TierModel
import com.example.tyrlost.ui.theme.tierColor1
import com.example.tyrlost.ui.theme.tierColor2
import com.example.tyrlost.ui.theme.tierColor3
import com.example.tyrlost.ui.theme.tierColor4
import com.example.tyrlost.ui.theme.tierColor5
import java.io.File


@Composable
fun TierListImageComponent(
    tierListModel: TierListModel
) {

    Column(Modifier.fillMaxWidth()) {
        Box(contentAlignment = Alignment.Center){
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = tierListModel.name,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }

        Column(Modifier.fillMaxWidth()) {
            tierListModel.tiers.forEach { TierImageComponent(it) }
        }
    }
}

@Preview
@Composable
fun View() {

    val file = File("", "").toUri()
    val tlm = TierListModel(
        listOf(
            TierModel("S", tierColor1, listOf(file, file)),
            TierModel("A", tierColor2, listOf(file)),
            TierModel("B", tierColor3, listOf(file, file, file)),
            TierModel("C", tierColor4, listOf(file)),
            TierModel("D", tierColor5, listOf(file)),
        )
    )

    TierListImageComponent(tlm)
}
