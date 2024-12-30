package com.example.tyrlost.models

import android.net.Uri
import androidx.compose.ui.graphics.Color
import com.example.tyrlost.ui.theme.tierColor16
import kotlinx.serialization.Serializable

@Serializable
data class TierModel(
    val name: String = "",
    val color: @Serializable(ColorSerializer::class) Color = tierColor16,
    val images: List<@Serializable(UriSerializer::class) Uri> = emptyList(),
)
