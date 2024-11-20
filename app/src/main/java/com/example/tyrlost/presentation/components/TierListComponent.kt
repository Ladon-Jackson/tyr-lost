package com.example.tyrlost.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tyrlost.presentation.TierListViewModel
import com.example.tyrlost.presentation.models.TierModel
import com.example.tyrlost.ui.theme.TyrlostTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect


@Composable
fun TierListComponent(tierListViewModel: TierListViewModel = viewModel()) {

    val a: List<TierModel> by tierListViewModel.tiers.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        itemsIndexed(a) { index, tierModel ->
            TierComponent(
                tierModel = tierModel,
                index = index,
                updateTiers = {image, to -> tierListViewModel.updateTiers(image, to)}
            )
        }
    }

}

@Preview(showBackground = false)
@Composable
private fun TierComponentPreview() {
    TyrlostTheme {
        TierListComponent()
    }
}


