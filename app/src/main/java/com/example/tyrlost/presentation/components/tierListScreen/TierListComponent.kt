package com.example.tyrlost.presentation.components.tierListScreen

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tyrlost.models.TierListModel
import com.example.tyrlost.presentation.components.tierListScreen.controls.ControlsComponent
import com.example.tyrlost.presentation.components.tierListScreen.tierDialog.TierDialogComponent
import com.example.tyrlost.presentation.viewModels.CurrentSelectionViewModel
import com.example.tyrlost.presentation.viewModels.TierListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi


@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun TierListComponent(
    id: String,
    navigateToMain: () -> Unit,
    modifier: Modifier = Modifier,
    currentSelectionViewModel: CurrentSelectionViewModel = viewModel(),
    tierListViewModel: TierListViewModel =
        hiltViewModel<TierListViewModel, TierListViewModel.TierListViewModelFactory> { it.create(id) },
) {

    val tierList: TierListModel by tierListViewModel.tierList.collectAsStateWithLifecycle()
    val currentTierOpen: Int? by currentSelectionViewModel.currentTierOpen.collectAsStateWithLifecycle()
    val currentImageSelected: Uri? by currentSelectionViewModel.currentImageSelected.collectAsStateWithLifecycle()

    currentTierOpen?.let {
        TierDialogComponent(
            index = it,
            name = tierList.tiers[it].name,
            color = tierList.tiers[it].color,
            onDismiss = currentSelectionViewModel::closeTierDialog,
            onTierUpdate = tierListViewModel::updateTierDetails,
            onDelete = tierListViewModel::removeTier
        )
    }

    Column(modifier = modifier) {

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = tierList.name,
                textAlign = TextAlign.Center
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(1f)
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
            unlistedImages = tierList.unlistedTier.images,                                              //TODO change back to this when fixed                   unlistedImages = tierList.unlistedImages,
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
