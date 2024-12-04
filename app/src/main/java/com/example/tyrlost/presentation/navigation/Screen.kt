package com.example.tyrlost.presentation.navigation

sealed class Screen(val route: String) {

    data object TierList : Screen("tierList")
    data object Main : Screen("main")
}