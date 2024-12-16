package com.example.tyrlost.models

import android.net.Uri
import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Serializable


data class TierModel(
    val name: String,
    val color: Color,
    @Serializable(with = UriListSerializer::class) val images: List<Uri> = emptyList(),
)