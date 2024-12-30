package com.example.tyrlost.presentation.components.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tyrlost.models.TierListModel


@Composable
fun MainComponent(
    modifier: Modifier = Modifier,
    navigateToTierList: (String) -> Unit,
    createNewTierList: () -> Unit,
    deleteTier: (TierListModel) -> Unit,
    tierLists: List<TierListModel>,
    deleteAll: () -> Unit,
) {

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Red)) {
        item {

            Box(
                modifier = Modifier
                    .height(100.dp)
                    .background(Color.White)
                    .fillParentMaxWidth()
                    .clickable {
                        createNewTierList()
                    }
            ) {
                Text(
                    text = "Create New Tier List",
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }

        items(tierLists) { tierList ->
            TierListBoxComponent(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .clickable { navigateToTierList(tierList.id.toString()) },
                deleteTier = {deleteTier(tierList)},
                text = tierList.name,
            )
        }

//        TODO delete this is just for testing stuff
//        item {
//            Button(
//                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
//                onClick = deleteAll
//            ) { Text("DELETE") }
//        }
    }
}
