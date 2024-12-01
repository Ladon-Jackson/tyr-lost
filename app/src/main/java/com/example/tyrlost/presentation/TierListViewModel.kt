package com.example.tyrlost.presentation

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.tyrlost.presentation.models.TierModel
import com.example.tyrlost.presentation.models.defaultTiers
import com.example.tyrlost.ui.theme.redTier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


class TierListViewModel : ViewModel() {

    private val _currentTierOpen: MutableStateFlow<Int?> = MutableStateFlow(null)
    private val _unlistedImages: MutableStateFlow<List<Uri>> = MutableStateFlow(emptyList())
    private val _currentImageSelected: MutableStateFlow<Uri?> = MutableStateFlow(null)
    private val _tiers: MutableStateFlow<List<TierModel>> = MutableStateFlow(defaultTiers)

    val currentTierOpen: StateFlow<Int?> = _currentTierOpen
    val tiers: StateFlow<List<TierModel>> = _tiers
    val unlistedImages: StateFlow<List<Uri>> = _unlistedImages
    val currentImageSelected: StateFlow<Uri?> = _currentImageSelected

    fun openTierDialog(openTierIndex: Int) = _currentTierOpen.update { openTierIndex }
    fun closeTierDialog() = _currentTierOpen.update { null }

    fun addNewImages(newImages: List<Uri>) =
        _unlistedImages.update { newImages + it }

    fun updateImageSelected(updatedImage: Uri) =
        _currentImageSelected.update {
            if (it != null) null
            else updatedImage
        }

    fun removeTier(removedIndex: Int) = _tiers.update {
        it.filterIndexed { index, _ -> removedIndex != index }
    }

    fun addTier() = _tiers.update { it.plus(TierModel("", redTier)) }

    fun updateTierName(changeIndex: Int, newName: String) = _tiers.update {
        it.mapIndexed { idx, tier ->
            if (idx == changeIndex) tier.copy(name = newName)
            else tier
        }
    }

    fun moveImageToUnlisted(image: Uri) {
        _tiers.update { removeImageTiers(image, it) }
        _unlistedImages.update { addImage(image, removeImageList(image, it)) }
    }


    fun moveImageToTier(updatedTierIndex: Int, image: Uri) {
        _unlistedImages.update { removeImageList(image, it) }
        _tiers.update { addImage(image, removeImageTiers(image, it), updatedTierIndex) }
    }

    fun moveImageToDestinationImageTiers(movingImage: Uri, destinationImage: Uri) {
        _unlistedImages.update {
            moveImageToDestinationImageList(
                movingImage = movingImage,
                destinationImage = destinationImage,
                images = it,
            )
        }
        _tiers.update { moveImageToDestinationImageTiers(movingImage, destinationImage, it) }
    }

    private fun addImage(
        image: Uri,
        tierList: List<TierModel>,
        tierIndex: Int,
    ): List<TierModel> =
        tierList.mapIndexed { currentIndex, tierModel ->
            when {
                tierIndex == currentIndex ->
                    tierModel.copy(images = addImage(image, tierModel.images))

                else -> tierModel
            }
        }

    private fun addImage(image: Uri, images: List<Uri>): List<Uri> =
        listOf(image) + images

    private fun removeImageTiers(image: Uri, tierList: List<TierModel>): List<TierModel> =
        tierList.map { it.copy(images = removeImageList(image, it.images)) }

    private fun removeImageList(image: Uri, list: List<Uri>): List<Uri> =
        list.filterNot { item -> item == image }


    private fun moveImageToDestinationImageTiers(
        movingImage: Uri,
        destinationImage: Uri,
        tiers: List<TierModel>,
    ): List<TierModel> = tiers.mapIndexed() { idx, thing ->
        thing.copy(
            images = moveImageToDestinationImageList(
                movingImage = movingImage,
                destinationImage = destinationImage,
                images = thing.images
            )
        )
    }

    private fun moveImageToDestinationImageList(
        movingImage: Uri,
        destinationImage: Uri,
        images: List<Uri>,
    ): List<Uri> {

        val imageListWithMovingImageRemoved = removeImageList(movingImage, images)
        val destinationImageIndex = imageListWithMovingImageRemoved.indexOf(destinationImage)

        if (destinationImageIndex < 0) return imageListWithMovingImageRemoved

        val imagesBeforeTarget = imageListWithMovingImageRemoved
            .filterIndexed { index, _ -> index <= destinationImageIndex }

        val imagesAfterTarget = imageListWithMovingImageRemoved
            .filterIndexed { index, _ -> index > destinationImageIndex }

        return imagesBeforeTarget + listOf(movingImage) + imagesAfterTarget
    }
}
