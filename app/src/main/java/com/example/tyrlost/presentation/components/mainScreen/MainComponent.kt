package com.example.tyrlost.presentation.components.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.tyrlost.presentation.models.defaultTierList


@Composable
fun MainComponent(
    modifier: Modifier = Modifier,
    navigateToTierList: () -> Unit,
) {

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Red)) {
        item {
            TierListBoxComponent(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .clickable { navigateToTierList() },
                tierListModel = defaultTierList,
            )
        }
    }
}
