package com.example.tyrlost.presentation.components.tierListScreen.tierList

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tyrlost.models.TierListModel
import com.example.tyrlost.presentation.components.tierListScreen.footer.FooterComponent
import com.example.tyrlost.presentation.components.tierListScreen.header.HeaderComponent
import com.example.tyrlost.presentation.components.tierListScreen.tierDialog.TierDialogComponent
import com.example.tyrlost.presentation.components.tierListScreen.tierImageDialog.SaveTierImageDialogComponent
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
    val tierImageDialogIsOpen: Boolean by currentSelectionViewModel.tierImageDialogOpen.collectAsStateWithLifecycle()

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

    if(tierImageDialogIsOpen) SaveTierImageDialogComponent(
        tierListModel = tierList,
        onDismiss = { currentSelectionViewModel.setImageTierDialogOpen(false) }
    )

    Column(modifier = modifier) {

        HeaderComponent(
            tierListName = tierList.name,
            onBack = navigateToMain
        )


        LazyColumn(
            modifier = Modifier
                .padding(3.dp)
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

        FooterComponent(
            unlistedImages = tierList.unlistedTier.images,                                              //TODO change back to this when fixed                   unlistedImages = tierList.unlistedImages,
            currentImageSelected = currentImageSelected,
            updateImageSelected = currentSelectionViewModel::updateImageSelected,
            addTier = tierListViewModel::addTier,
            addImages = tierListViewModel::addNewImages,
            saveTierAsImage = { currentSelectionViewModel.setImageTierDialogOpen(true) },
            moveImageToUnlisted = tierListViewModel::moveImageToUnlisted,
            moveImageToDestinationImage = tierListViewModel::moveImageToDestinationImageTiers,
            deleteImage = tierListViewModel::deleteImage
        )
    }
}
