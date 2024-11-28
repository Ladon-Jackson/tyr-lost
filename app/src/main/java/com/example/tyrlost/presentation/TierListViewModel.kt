package com.example.tyrlost.presentation

import androidx.lifecycle.ViewModel
import com.example.tyrlost.presentation.models.TierModel
import com.example.tyrlost.presentation.models.testTiers
import com.example.tyrlost.ui.theme.redTier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.update


class TierListViewModel: ViewModel() {

    val tiers: MutableStateFlow<List<TierModel>> = MutableStateFlow(testTiers)
    val currentTierOpen: MutableStateFlow<Int?> = MutableStateFlow(null)

    fun removeTier(removedIndex: Int) = tiers.getAndUpdate {
        it.filterIndexed { index, _ ->
            removedIndex != index
        }
    }

    fun addTier() = tiers.getAndUpdate { it.plus(TierModel("", redTier)) }

    fun updateTierName(changeIndex: Int, newName: String) = tiers.getAndUpdate {
        it.mapIndexed { idx, tier -> when {
            idx == changeIndex -> tier.copy(name = newName)
            else -> tier
        } }
    }

    fun moveImageToTier(to: Int, image: Int) = tiers.getAndUpdate {

        val tiersWithRemovedItem: List<TierModel> = it.map { tier ->
            tier.copy(images = tier.images.filterNot { item -> item == image })
        }

        tiersWithRemovedItem.mapIndexed { idx, tier ->
           when {
               idx == to -> tier.copy(images = tier.images.plus(image))
               else -> tier
           }
        }
    }

    fun openDialog(openTierIndex: Int) = currentTierOpen.update { openTierIndex }

    fun closeDialog() = currentTierOpen.update { null }
}
