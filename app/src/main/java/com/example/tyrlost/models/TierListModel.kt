package com.example.tyrlost.models

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tyrlost.ui.theme.blueTier
import com.example.tyrlost.ui.theme.greenTier
import com.example.tyrlost.ui.theme.orangeTier
import com.example.tyrlost.ui.theme.redTier
import com.example.tyrlost.ui.theme.yellowTier
import kotlinx.serialization.Serializable


@Entity(tableName = "tierLists")
data class TierListModel(
    val name: String,
    @Serializable(with = UriListSerializer::class) val unlistedImages: List<Uri>,
    @Serializable val tiers: List<TierModel>,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
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
