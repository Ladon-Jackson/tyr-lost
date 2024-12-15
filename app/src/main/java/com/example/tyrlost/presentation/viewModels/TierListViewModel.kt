package com.example.tyrlost.presentation.viewModels

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tyrlost.models.TierListDao
import com.example.tyrlost.models.TierListModel
import com.example.tyrlost.models.TierModel
import com.example.tyrlost.models.defaultTierList
import com.example.tyrlost.services.FileService
import com.example.tyrlost.ui.theme.redTier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

//TODO DI
//@HiltViewModel
//class TierListViewModel @Inject constructor(val fileService: FileService): ViewModel() {
class TierListViewModel(
    val dao: TierListDao
): ViewModel() {

    private val _tierList: MutableStateFlow<TierListModel> = MutableStateFlow(defaultTierList)
    val tierList: StateFlow<TierListModel> = _tierList

    fun addNewImages(newImageUris: List<Uri>, context: Context) = _tierList.update {
        //TODO DI
        val updatedUris = FileService(context).saveImagesToInternalStorage(newImageUris)
        val updatedTierList = it.copy(unlistedImages = updatedUris + it.unlistedImages)
        viewModelScope.launch { dao.upsertTierList(updatedTierList) }
        updatedTierList
    }

    fun removeTier(removedIndex: Int) = _tierList.update {
        val updatedTierList = it.copy(tiers = it.tiers.filterIndexed { index, _ -> removedIndex != index })
        viewModelScope.launch { dao.upsertTierList(updatedTierList) }
        updatedTierList
    }

    fun addTier() = _tierList.update {
        val updatedTierList = it.copy(tiers = it.tiers.plus(TierModel("", redTier)))
        viewModelScope.launch { dao.upsertTierList(updatedTierList) }
        updatedTierList
    }

    fun updateTierName(changeIndex: Int, newName: String) = _tierList.update {
        val updatedTierList = it.copy(
            tiers = it.tiers.mapIndexed { idx, tier ->
                if (idx == changeIndex) tier.copy(name = newName)
                else tier
            }
        )
        viewModelScope.launch { dao.upsertTierList(updatedTierList) }
        updatedTierList
    }

    fun moveImageToUnlisted(image: Uri) = _tierList.update {
        val updatedTierList = it.copy(
            tiers = removeImageTiers(image, it.tiers),
            unlistedImages = addImage(image, removeImageList(image, it.unlistedImages))
        )
        viewModelScope.launch { dao.upsertTierList(updatedTierList) }
        updatedTierList
    }

    fun moveImageToTier(updatedTierIndex: Int, image: Uri) = _tierList.update {
        val updatedTierList = it.copy(
            unlistedImages = removeImageList(image, it.unlistedImages),
            tiers = addImage(image, removeImageTiers(image, it.tiers), updatedTierIndex)
        )
        viewModelScope.launch { dao.upsertTierList(updatedTierList) }
        updatedTierList
    }

    fun moveImageToDestinationImageTiers(movingImage: Uri, destinationImage: Uri) = _tierList.update {
        val updatedTierList = it.copy(
            unlistedImages = moveImageToDestinationImageList(
                movingImage = movingImage,
                destinationImage = destinationImage,
                images = it.unlistedImages,
            ),
            tiers = it.tiers.map { tier -> tier.copy(
                images = moveImageToDestinationImageList(
                    movingImage = movingImage,
                    destinationImage = destinationImage,
                    images = tier.images
                )
            ) }
        )
        viewModelScope.launch { dao.upsertTierList(updatedTierList) }
        updatedTierList
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

    private fun moveImageToDestinationImageList(
        movingImage: Uri,
        destinationImage: Uri,
        images: List<Uri>,
    ): List<Uri> {

        val imageListWithMovingImageRemoved = removeImageList(movingImage, images)
        val destinationIndex = imageListWithMovingImageRemoved.indexOf(destinationImage)
        println(imageListWithMovingImageRemoved)

        if (destinationIndex < 0) return imageListWithMovingImageRemoved

//        TODO CHOOSE ONE OF THESE 3 OPTIONS

//        val mutList = imageListWithMovingImageRemoved.toMutableList()
//        mutList.add(destinationIndex + 1, movingImage)
//
//        return mutList.map { it }

        val imagesBeforeTarget = imageListWithMovingImageRemoved
            .subList(0, destinationIndex + 1)

        val imagesAfterTarget = imageListWithMovingImageRemoved
            .subList(destinationIndex + 1, imageListWithMovingImageRemoved.size)

        return imagesBeforeTarget + listOf(movingImage) + imagesAfterTarget

//        val imagesBeforeTarget = imageListWithMovingImageRemoved
//            .filterIndexed { index, _ -> index <= destinationIndex }
//
//        val imagesAfterTarget = imageListWithMovingImageRemoved
//            .filterIndexed { index, _ -> index > destinationIndex }
//
//        return imagesBeforeTarget + listOf(movingImage) + imagesAfterTarget
    }
}
