package com.example.tyrlost.presentation.components.tierListScreen

import android.net.Uri
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
import com.example.tyrlost.presentation.components.tierListScreen.controls.ControlsComponent
import com.example.tyrlost.presentation.components.tierListScreen.tierDialog.TierDialogComponent
import com.example.tyrlost.presentation.models.TierListModel
import com.example.tyrlost.presentation.viewModels.CurrentSelectionViewModel
import com.example.tyrlost.presentation.viewModels.TierListViewModel


@Composable
fun TierListComponent(
    modifier: Modifier = Modifier,
    navigateToMain: () -> Unit,
    tierListViewModel: TierListViewModel = viewModel(),
    currentSelectionViewModel: CurrentSelectionViewModel = viewModel(),
) {

    val tierList: TierListModel by tierListViewModel.tierList.collectAsStateWithLifecycle()
    val currentTierOpen: Int? by currentSelectionViewModel.currentTierOpen.collectAsStateWithLifecycle()
    val currentImageSelected: Uri? by currentSelectionViewModel.currentImageSelected.collectAsStateWithLifecycle()

    currentTierOpen?.let {
        TierDialogComponent(
            index = it,
            name = tierList.tiers[it].name,
            onDismiss = currentSelectionViewModel::closeTierDialog,
            onRename = tierListViewModel::updateTierName,
            onDelete = tierListViewModel::removeTier
        )
    }

    Column(modifier = modifier) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            itemsIndexed(tierList.tiers) { index, tierModel ->
                TierComponent(
                    tierModel = tierModel,
                    index = index,
                    currentImageSelected = currentImageSelected,
                    updateImageSelected = currentSelectionViewModel::updateImageSelected,
                    openTierDialog = currentSelectionViewModel::openTierDialog,
                    moveImageToTier = tierListViewModel::moveImageToTier,
                    moveImageToDestinationImage = tierListViewModel::moveImageToDestinationImageTiers
                )
            }
        }

        ControlsComponent(
            nextTierIndex = tierList.tiers.size,
            unlistedImages = tierList.unlistedImages,
            currentImageSelected = currentImageSelected,
            updateImageSelected = currentSelectionViewModel::updateImageSelected,
            addTier = tierListViewModel::addTier,
            openTierDialog = currentSelectionViewModel::openTierDialog,
            addImages = tierListViewModel::addNewImages,
            moveImageToUnlisted = tierListViewModel::moveImageToUnlisted,
            moveImageToDestinationImage = tierListViewModel::moveImageToDestinationImageTiers
        )
    }
}