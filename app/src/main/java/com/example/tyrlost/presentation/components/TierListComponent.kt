package com.example.tyrlost.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tyrlost.presentation.TierListViewModel
import com.example.tyrlost.presentation.models.TierModel
import com.example.tyrlost.ui.theme.TyrlostTheme


@Composable
fun TierListComponent(tierListViewModel: TierListViewModel = viewModel()) {

    val tierList: MutableState<List<TierModel>> = remember { tierListViewModel.tiers }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        tierList.component1().forEach { tierModel ->
            TierComponent(tierModel, updateTiers = tierListViewModel::updateTiers)
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


