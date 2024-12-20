package com.example.tyrlost.presentation.viewModels

import android.net.Uri
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tyrlost.helpers.FileHelper
import com.example.tyrlost.models.TierListDao
import com.example.tyrlost.models.TierListModel
import com.example.tyrlost.models.TierModel
import com.example.tyrlost.models.defaultTierList
import com.example.tyrlost.ui.theme.redTier
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@kotlinx.coroutines.ExperimentalCoroutinesApi
@HiltViewModel
class TierListViewModel @Inject constructor(
    private val fileService: FileHelper,
    private val dao: TierListDao
): ViewModel() {

    val tierList: StateFlow<TierListModel> = dao
        .getTierList()
        .map { it ?: defaultTierList }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = defaultTierList
        )

    fun addNewImages(newImageUris: List<Uri>) = tierList.value.let {
        val updatedUris = fileService.saveImagesToInternalStorage(newImageUris)
        val updatedTierList = it.copy(unlistedTier = TierModel(images = updatedUris + it.unlistedTier.images))
        viewModelScope.launch { dao.upsertTierList(updatedTierList) }
    }

    fun removeTier(removedIndex: Int) = tierList.value.let {
        val updatedTierList =
            it.copy(tiers = it.tiers.filterIndexed { index, _ -> removedIndex != index })
        viewModelScope.launch { dao.upsertTierList(updatedTierList) }
    }

    fun addTier() = tierList.value.let {
        val updatedTierList = it.copy(tiers = it.tiers.plus(TierModel("", redTier)))
        viewModelScope.launch { dao.upsertTierList(updatedTierList) }
    }

//    fun updateTierName(changeIndex: Int, newName: String) = tierList.value.let {
//        val updatedTierList = it.copy(
//            tiers = it.tiers.mapIndexed { idx, tier ->
//                if (idx == changeIndex) tier.copy(name = newName)
//                else tier
//            }
//        )
//        viewModelScope.launch { dao.upsertTierList(updatedTierList) }
//    }

    fun updateTierDetails(changeIndex: Int, newName: String? = null, newColor: Color? = null) = tierList.value.let {
        val updatedTierList = it.copy(
            tiers = it.tiers.mapIndexed { idx, tier ->
                if (idx == changeIndex) tier.copy(name = newName ?: tier.name, color = newColor ?: tier.color)
                else tier
            }
        )
        viewModelScope.launch { dao.upsertTierList(updatedTierList) }
    }

//    fun updateTierColor(changeIndex: Int, newColor: Color) = tierList.value.let {
//        val updatedTierList = it.copy(
//            tiers = it.tiers.mapIndexed { idx, tier ->
//                if (idx == changeIndex) tier.copy(color = newColor)
//                else tier
//            }
//        )
//        viewModelScope.launch { dao.upsertTierList(updatedTierList) }
//    }

    fun moveImageToUnlisted(image: Uri) = tierList.value.let {
        val updatedTierList = it.copy(
            tiers = removeImageTiers(image, it.tiers),
            unlistedTier = TierModel(images = addImage(image, removeImageList(image, it.unlistedTier.images))) //TODO change back to this when fixed                unlistedImages = addImage(image, removeImageList(image, it.unlistedImages))
        )
        viewModelScope.launch { dao.upsertTierList(updatedTierList) }
        updatedTierList
    }

    fun moveImageToTier(updatedTierIndex: Int, image: Uri) = tierList.value.let {
        val updatedTierList = it.copy(
            unlistedTier = TierModel(images = removeImageList(image, it.unlistedTier.images)),              //TODO change back to this when fixed             unlistedImages = removeImageList(image, it.unlistedImages),
            tiers = addImage(image, removeImageTiers(image, it.tiers), updatedTierIndex)
        )
        viewModelScope.launch { dao.upsertTierList(updatedTierList) }
    }

    fun moveImageToDestinationImageTiers(movingImage: Uri, destinationImage: Uri) =
        tierList.value.let {
            val updatedTierList = it.copy(
                unlistedTier = TierModel(images = moveImageToDestinationImageList(                      //TODO change back to this when fixed unlistedTier = moveImageToDestinationImageList(
                    movingImage = movingImage,
                    destinationImage = destinationImage,
                    images = it.unlistedTier.images,                                                    //TODO change back to this when fixed              images = it.unlistedImages,
                )),
                tiers = it.tiers.map { tier ->
                    tier.copy(
                        images = moveImageToDestinationImageList(
                            movingImage = movingImage,
                            destinationImage = destinationImage,
                            images = tier.images
                        )
                    )
                }
            )
            viewModelScope.launch { dao.upsertTierList(updatedTierList) }
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
                else ->
                    tierModel
            }
        }

    private fun addImage(image: Uri, images: List<Uri>): List<Uri> =
        listOf(image) + images

    private fun removeImageTiers(image: Uri, tierList: List<TierModel>): List<TierModel> =
        tierList.map { it.copy(images = removeImageList(image, it.images)) }

    private fun removeImageList(image: Uri, list: List<Uri>): List<Uri> =
        list.filterNot { item -> item == image }

    private fun moveImageToDestinationImageList(
        movingImage: Uri,
        destinationImage: Uri,
        images: List<Uri>,
    ): List<Uri> {

        val imageListWithMovingImageRemoved = removeImageList(movingImage, images)
        val destinationIndex = imageListWithMovingImageRemoved.indexOf(destinationImage)
        println(imageListWithMovingImageRemoved)

        if (destinationIndex < 0) return imageListWithMovingImageRemoved

        val imagesBeforeTarget = imageListWithMovingImageRemoved
            .subList(0, destinationIndex + 1)

        val imagesAfterTarget = imageListWithMovingImageRemoved
            .subList(destinationIndex + 1, imageListWithMovingImageRemoved.size)

        return imagesBeforeTarget + listOf(movingImage) + imagesAfterTarget
    }
}
