package com.example.tyrlost.presentation.models

import android.net.Uri
import com.example.tyrlost.ui.theme.blueTier
import com.example.tyrlost.ui.theme.greenTier
import com.example.tyrlost.ui.theme.orangeTier
import com.example.tyrlost.ui.theme.redTier
import com.example.tyrlost.ui.theme.yellowTier

data class TierListModel(
    val name: String,
    val unlistedImages: List<Uri>,
    val tiers: List<TierModel>,
)

val defaultTierList: TierListModel = TierListModel(
    name = "First Tier List",
    unlistedImages = listOf(),
    tiers = listOf(
        TierModel("S", redTier),
        TierModel("A", orangeTier),
        TierModel("B", yellowTier),
        TierModel("C", greenTier),
        TierModel("D", blueTier)
    )
)
