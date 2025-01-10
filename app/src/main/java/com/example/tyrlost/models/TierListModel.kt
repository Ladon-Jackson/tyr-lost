package com.example.tyrlost.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tyrlost.ui.theme.tierColor1
import com.example.tyrlost.ui.theme.tierColor2
import com.example.tyrlost.ui.theme.tierColor3
import com.example.tyrlost.ui.theme.tierColor4
import com.example.tyrlost.ui.theme.tierColor5


@Entity(tableName = "tierLists")
data class TierListModel(
    val tiers: List<TierModel> = defaultTiers,
    val unlistedTier: TierModel = TierModel(), //TODO make converter work properly so this can be switched back to List<Uri>
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String = "Anime 2024",
)

val defaultTiers = listOf(
    TierModel("S", tierColor1),
    TierModel("A", tierColor2),
    TierModel("B", tierColor3),
    TierModel("C", tierColor4),
    TierModel("D", tierColor5)
)
