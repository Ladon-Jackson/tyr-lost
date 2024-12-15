package com.example.tyrlost.models

import android.net.Uri
import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Serializable


@Serializable
data class TierModel(
    val name: String,
    val color: Color,
    val images: List<Uri> = emptyList(),
)