package com.example.tyrlost.presentation.navigation

sealed class Screen(val route: String) {

    data object TierList: Screen("tierList/{id}") {
        fun route(id: String) = "tierList/$id"
    }

    data object Main: Screen("main")
}