package com.example.tyrlost.presentation

import androidx.lifecycle.ViewModel
import com.example.tyrlost.presentation.models.TierModel
import com.example.tyrlost.presentation.models.testTiers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class TierListViewModel: ViewModel() {

    val tiers: MutableStateFlow<List<TierModel>> = MutableStateFlow(testTiers)

    fun updateTiers(image: Int, to: Int) {

        val mutableList = tiers.value.toMutableList()

        val tiersRemovedNew = mutableList.map{ tier ->
            val mutableImages = tier.images.toMutableList()
            mutableImages.remove(image)
            mutableImages
        }

        val test = tiersRemovedNew.apply {this[to].add(image)}

        val finished = tiers.value.mapIndexed { index, tier ->
            tier.copy(images = test[index])
        }

//        val tiersWithRemovedItem: List<TierModel> = tiers.value.map { tier ->
//            tier.copy(images = tier.images.filterNot { item -> item == image })
//        }

//        val updatedTiers: List<TierModel> = tiersWithRemovedItem.mapIndexed { idx, tier ->
//           when{
//               idx == to -> tier.copy(images = tier.images.plus(image))
//               else -> tier
//           }
//        }

        println("before: ${tiers.value.joinToString { it.toString() }}")
        tiers.update { finished }
        println("after: ${finished.joinToString { it.toString() }}")
    }
}
