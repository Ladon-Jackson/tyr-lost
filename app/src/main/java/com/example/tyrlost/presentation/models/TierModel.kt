package com.example.tyrlost.presentation.models

import android.net.Uri
import androidx.compose.ui.graphics.Color
import com.example.tyrlost.ui.theme.blueTier
import com.example.tyrlost.ui.theme.greenTier
import com.example.tyrlost.ui.theme.orangeTier
import com.example.tyrlost.ui.theme.redTier
import com.example.tyrlost.ui.theme.yellowTier


data class TierModel(val name: String, val color: Color, val images: List<Uri> = emptyList())

val defaultTiers = listOf(
    TierModel("S", redTier),
    TierModel("A", orangeTier),
    TierModel("B", yellowTier),
    TierModel("C", greenTier),
    TierModel("D", blueTier)
)
