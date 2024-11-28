package com.example.tyrlost.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tyrlost.presentation.TierListViewModel
import com.example.tyrlost.presentation.components.dialog.TierDialogComponent
import com.example.tyrlost.presentation.models.TierModel


@Composable
fun TierListComponent(tierListViewModel: TierListViewModel = viewModel()) {

    val tiers: List<TierModel> by tierListViewModel.tiers.collectAsStateWithLifecycle()
    val selectedTierIndex: Int? by tierListViewModel.currentTierOpen.collectAsStateWithLifecycle()

    //TODO hate this '!!' thing find a better way (kotlin equivalent to mapping on an Option)
    if(selectedTierIndex != null) {
        TierDialogComponent(
            index = selectedTierIndex!!,
            name = tiers[selectedTierIndex!!].name,
            onDismiss = tierListViewModel::closeDialog,
            onDelete = tierListViewModel::removeTier,
            onRename = tierListViewModel::updateTierName
        )
    }
    Column {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            itemsIndexed(tiers) { index, tierModel ->
                TierComponent(
                    tierModel = tierModel,
                    index = index,
                    updateTiers = tierListViewModel::moveImageToTier,
                    openDialog = tierListViewModel::openDialog
                )
            }
        }

        AddButtonsComponent(
            onAddTier = {
                tierListViewModel.addTier()
                tierListViewModel.openDialog(tiers.size)
            },
            addImage = {

            }
        )
    }
}
