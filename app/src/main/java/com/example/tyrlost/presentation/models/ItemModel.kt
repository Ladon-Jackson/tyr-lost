package com.example.tyrlost.presentation.models

import com.example.tyrlost.R

data class ItemModel(val name: String, val image: Int)

val testItems = listOf(
    ItemModel("One", R.drawable.image1),
    ItemModel("Two", R.drawable.image2),
    ItemModel("Three", R.drawable.image3)
)
