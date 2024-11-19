package com.example.tyrlost.presentation.models

import androidx.compose.ui.graphics.Color
import com.example.tyrlost.R
import com.example.tyrlost.ui.theme.*

data class TierModel(val name: String, val color: Color, val items: List<Int> = emptyList())

val testTiers = listOf(
    TierModel("S", redTier, listOf(R.drawable.image1, R.drawable.image2, R.drawable.image3)),
    TierModel("A", orangeTier),
    TierModel("B", yellowTier),
    TierModel("C", greenTier),
    TierModel("D", blueTier)
)

//data class TierModel(val name: String, val color: Color, val items: List<ItemModel> = emptyList())
//
//val testTiers = listOf(
//    TierModel("S", redTier, items = testItems),
//    TierModel("A", orangeTier),
//    TierModel("B", yellowTier),
//    TierModel("C", greenTier),
//    TierModel("D", blueTier)
//)
