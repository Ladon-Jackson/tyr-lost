package com.example.tyrlost.models

import android.net.Uri
import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Serializable

@Serializable
data class TierModel(
    val name: String = "untiered",
    val color: @Serializable(ColorSerializer::class) Color = Color(0xFFFFFFFF),
    val images: List<@Serializable(UriSerializer::class) Uri> = emptyList(),
)
