package com.example.tyrlost.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.tyrlost.presentation.models.TierModel
import com.example.tyrlost.presentation.models.testTiers


class TierListViewModel: ViewModel() {

    var tiers: MutableState<List<TierModel>> = mutableStateOf(testTiers)

//    val images = listOf<Int>()
//    val tiersNames = listOf<String>()
//    var testTiers = mutableStateOf()

    fun updateTiers(image: Int, to: Int) {

        val tiersWithRemovedItem: List<TierModel> = tiers.value.map { tier ->
            tier.copy(items = tier.items.filterNot { item -> item == image })
        }

        val updatedTiers: List<TierModel> = tiersWithRemovedItem.mapIndexed { idx, tier ->
           when{
               idx == to -> tier.copy(items = tier.items.plus(image))
               else -> tier
           }
        }

        println("DEBUG: " + updatedTiers.map{x -> x.items})

        tiers.value = updatedTiers
    }
}
