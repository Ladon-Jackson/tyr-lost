package com.example.tyrlost.presentation

import androidx.lifecycle.ViewModel
import com.example.tyrlost.presentation.models.TierModel
import com.example.tyrlost.presentation.models.testTiers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.withIndex


class TierListViewModel: ViewModel() {

    val tiers: MutableStateFlow<List<TierModel>> = MutableStateFlow(testTiers)
    val currentTierOpen: MutableStateFlow<Int?> = MutableStateFlow(null)

    fun removeTier(removedIndex: Int) {
        tiers.update  {tiers.value.filterIndexed { index, _ ->
            removedIndex != index
        } }
    }

    fun updateTierName(changeIndex: Int, newName: String) {
        tiers.getAndUpdate {
            it.mapIndexed { idx, tier -> when {
                idx == changeIndex -> tier.copy(name = newName)
                else -> tier
            } }
        }
    }

    fun moveImageToTier(to: Int, image: Int) {

        val tiersWithRemovedItem: List<TierModel> = tiers.value.map { tier ->
            tier.copy(images = tier.images.filterNot { item -> item == image })
        }

        val updatedTiers: List<TierModel> = tiersWithRemovedItem.mapIndexed { idx, tier ->
           when {
               idx == to -> tier.copy(images = tier.images.plus(image))
               else -> tier
           }
        }

        tiers.update { updatedTiers }
    }

    fun openDialog(openTierIndex: Int) {
        currentTierOpen.update { openTierIndex }
    }

    fun closeDialog() {
        currentTierOpen.update { null }
    }
}
