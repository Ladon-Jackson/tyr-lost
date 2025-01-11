package com.example.tyrlost.presentation.components.mainScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tyrlost.models.TierListModel
import com.example.tyrlost.presentation.components.common.IconButtonComponent
import com.example.tyrlost.presentation.components.common.Icons


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainComponent(
    modifier: Modifier = Modifier,
    navigateToTierList: (String) -> Unit,
    createNewTierList: () -> Unit,
    deleteTier: (TierListModel) -> Unit,
    tierLists: List<TierListModel>
) {

    Column(modifier = modifier.fillMaxWidth()) {

        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Tyr Lost",
                    textAlign = TextAlign.Center
                )
            }
        )



        LazyColumn(/*modifier = Modifier.weight(1f)*/) {
            item {
                Card(
                    modifier = Modifier
                        .height(100.dp)
                        .padding(4.dp)
                        .fillParentMaxWidth()
                        .clickable {
                            createNewTierList()
                        }
                ) {
                    Icon(
                        imageVector = Icons.add,
                        contentDescription = "Create new tier list",
                        modifier = Modifier
                            .aspectRatio(1f)
                            .fillMaxHeight()
                            .padding(16.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }

            items(tierLists) { tierList ->
                TierListBoxComponent(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .clickable { navigateToTierList(tierList.id.toString()) },
                    deleteTier = { deleteTier(tierList) },
                    text = tierList.name,
                )
            }
        }
    }
}
