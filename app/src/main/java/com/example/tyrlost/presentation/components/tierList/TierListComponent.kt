package com.example.tyrlost.presentation.components.tierList

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tyrlost.presentation.TierListViewModel
import com.example.tyrlost.presentation.components.TierDialog.TierDialogComponent
import com.example.tyrlost.presentation.models.TierModel


@Composable
fun TierListComponent(tierListViewModel: TierListViewModel = viewModel()) {

    val tiers: List<TierModel> by tierListViewModel.tiers.collectAsStateWithLifecycle()
    val untieredImages: List<Uri> by tierListViewModel.untieredImages.collectAsStateWithLifecycle()
    val currentTierOpen: Int? by tierListViewModel.currentTierOpen.collectAsStateWithLifecycle()
    val currentImageSelected: Int? by tierListViewModel.currentImageSelected.collectAsStateWithLifecycle()

    //TODO hate this '!!' thing find a better way (kotlin equivalent to mapping on an Option)
    if(currentTierOpen != null) {
        TierDialogComponent(
            index = currentTierOpen!!,
            name = tiers[currentTierOpen!!].name,
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
                    currentImageSelected = currentImageSelected,
                    selectImage = tierListViewModel::updateImageSelected,
                    updateTiers = tierListViewModel::moveImageToTier,
                    openDialog = tierListViewModel::openDialog
                )
            }
        }

        LazyRow(modifier = Modifier) {
            items(untieredImages) {
                NewImageComponent(
                    image = it,
                    isSelected = false,// TODO update to something like `isSelected = it == currentImageSelected,`
                    onClick = {
//                        tierListViewModel.addImage(it)
                    }
                )
            }
        }

        AddButtonsComponent(
            nextTierIndex = tiers.size,
            addTier = tierListViewModel::addTier,
            openDialog = tierListViewModel::openDialog,
            addImages = tierListViewModel::addImages
        )
    }
}
