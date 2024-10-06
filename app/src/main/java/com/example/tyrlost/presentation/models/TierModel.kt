package com.example.tyrlost.presentation.models

import androidx.compose.ui.graphics.Color
import com.example.tyrlost.ui.theme.*


data class TierModel(val name: String, val color: Color, val items: List<ItemModel> = emptyList())

val testTiers = listOf(
    TierModel("S", redTier, items = testItems),
    TierModel("A", orangeTier),
    TierModel("B", yellowTier),
    TierModel("C", greenTier),
    TierModel("D", blueTier)
)
