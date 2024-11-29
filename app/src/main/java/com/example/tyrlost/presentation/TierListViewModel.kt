package com.example.tyrlost.presentation

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.tyrlost.presentation.models.TierModel
import com.example.tyrlost.presentation.models.testTiers
import com.example.tyrlost.ui.theme.redTier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.update


class TierListViewModel: ViewModel() {

    val _tiers: MutableStateFlow<List<TierModel>> = MutableStateFlow(testTiers)
    val _untieredImages: MutableStateFlow<List<Uri>> = MutableStateFlow(emptyList())
    val _currentTierOpen: MutableStateFlow<Int?> = MutableStateFlow(null)
    val _currentImageSelected: MutableStateFlow<Int?> = MutableStateFlow(null)

    val tiers: StateFlow<List<TierModel>> = _tiers
    val untieredImages: StateFlow<List<Uri>> = _untieredImages
    val currentTierOpen: StateFlow<Int?> = _currentTierOpen
    val currentImageSelected: StateFlow<Int?> = _currentImageSelected

    fun addImages(newImages: List<Uri>) = _untieredImages.getAndUpdate {
        println("BEFORE: ${_untieredImages.value.size}")
        newImages + it
    }

    fun updateImageSelected(updatedImage: Int) = _currentImageSelected.getAndUpdate {
        when {
            it == updatedImage -> null
            else -> updatedImage
        }
    }

    fun removeTier(removedIndex: Int) = _tiers.getAndUpdate {
        it.filterIndexed { index, _ ->
            removedIndex != index
        }
    }

    fun addTier() = _tiers.getAndUpdate { it.plus(TierModel("", redTier)) }

    fun updateTierName(changeIndex: Int, newName: String) = _tiers.getAndUpdate {
        it.mapIndexed { idx, tier -> when {
            idx == changeIndex -> tier.copy(name = newName)
            else -> tier
        } }
    }

    fun moveImageToTier(updatedTierIndex: Int, image: Int) = _tiers.getAndUpdate {

        val tiersWithRemovedItem: List<TierModel> = it.map { tier ->
            tier.copy(images = tier.images.filterNot { item -> item == image })
        }

        tiersWithRemovedItem.mapIndexed { idx, tier ->
           when {
               idx == updatedTierIndex -> tier.copy(images = listOf(image) + tier.images)
               else -> tier
           }
        }
    }

    fun openDialog(openTierIndex: Int) = _currentTierOpen.update { openTierIndex }

    fun closeDialog() = _currentTierOpen.update { null }
}
