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

    val _currentTierOpen: MutableStateFlow<Int?> = MutableStateFlow(null)
    val _tiers: MutableStateFlow<List<TierModel>> = MutableStateFlow(testTiers)
    val _unlistedImages: MutableStateFlow<List<Uri>> = MutableStateFlow(emptyList())
    val _currentImageSelected: MutableStateFlow<Uri?> = MutableStateFlow(null)

    val currentTierOpen: StateFlow<Int?> = _currentTierOpen
    val tiers: StateFlow<List<TierModel>> = _tiers
    val unlistedImages: StateFlow<List<Uri>> = _unlistedImages
    val currentImageSelected: StateFlow<Uri?> = _currentImageSelected

    fun openTierDialog(openTierIndex: Int) = _currentTierOpen.update { openTierIndex }
    fun closeTierDialog() = _currentTierOpen.update { null }

    fun addNewImages(newImages: List<Uri>) =
        _unlistedImages.getAndUpdate { newImages + it }

    fun updateImageSelected(updatedImage: Uri) =
        _currentImageSelected.getAndUpdate { when {
            it == updatedImage -> null
            else -> updatedImage
        } }

    fun removeTier(removedIndex: Int) = _tiers.getAndUpdate {
        it.filterIndexed { index, _ -> removedIndex != index }
    }

    fun addTier() = _tiers.getAndUpdate { it.plus(TierModel("", redTier)) }

    fun updateTierName(changeIndex: Int, newName: String) = _tiers.getAndUpdate {
        it.mapIndexed { idx, tier -> when {
            idx == changeIndex -> tier.copy(name = newName)
            else -> tier
        } }
    }

    fun moveImageToUnlisted(updatedTierIndex: Int?, image: Uri) {
        _tiers.getAndUpdate { removeImageFromTierList(image, it) }
        _unlistedImages.getAndUpdate { addImageToList(
            image = image,
            images = removeImageFromList(image, it)
        ) }
    }

    fun moveImageToTier(updatedTierIndex: Int, image: Uri) {
        _unlistedImages.getAndUpdate { removeImageFromList(image, it) }
        _tiers.getAndUpdate { addImageToTierList(
            image = image,
            tierList = removeImageFromTierList(image, it),
            index = updatedTierIndex
        ) }
    }

    private fun addImageToTierList(image: Uri, tierList: List<TierModel>, index: Int): List<TierModel> =
        tierList.mapIndexed { currentIndex, tierModel -> when {
            index == currentIndex ->
                tierModel.copy(images = addImageToList(image, tierModel.images))
            else -> tierModel
        } }

    private fun addImageToList(image: Uri, images: List<Uri>): List<Uri> =
        listOf(image) + images

    private fun removeImageFromTierList(image: Uri, tierlist: List<TierModel>): List<TierModel> =
        tierlist.map { it.copy(images = removeImageFromList(image, it.images))}

    private fun removeImageFromList(image: Uri, list: List<Uri>): List<Uri> =
        list.filterNot { item -> item == image }

}
