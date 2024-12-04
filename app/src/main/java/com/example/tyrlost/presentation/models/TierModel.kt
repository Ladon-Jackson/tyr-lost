package com.example.tyrlost.presentation.models

import android.net.Uri
import androidx.compose.ui.graphics.Color


data class TierModel(val name: String, val color: Color, val images: List<Uri> = emptyList())
