package com.example.tyrlost.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tyrlost.ui.theme.blueTier
import com.example.tyrlost.ui.theme.greenTier
import com.example.tyrlost.ui.theme.orangeTier
import com.example.tyrlost.ui.theme.redTier
import com.example.tyrlost.ui.theme.yellowTier


@Entity(tableName = "tierLists")
data class TierListModel(
    val name: String,
    val tiers: List<TierModel>,
    val unlistedTier: TierModel = TierModel(), //TODO make converter work properly so this can be switched back to List<Uri>
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
)

val defaultTierList: TierListModel = TierListModel(
    name = "First Tier List",
    tiers = listOf(
        TierModel("S", redTier),
        TierModel("A", orangeTier),
        TierModel("B", yellowTier),
        TierModel("C", greenTier),
        TierModel("D", blueTier)
    )
)
