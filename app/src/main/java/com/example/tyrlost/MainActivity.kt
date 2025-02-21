package com.example.tyrlost

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tyrlost.models.TierListModel
import com.example.tyrlost.presentation.components.mainScreen.MainComponent
import com.example.tyrlost.presentation.components.tierListScreen.tierList.TierListComponent
import com.example.tyrlost.presentation.navigation.Screen
import com.example.tyrlost.presentation.viewModels.MultipleTierListsViewModel
import com.example.tyrlost.ui.theme.TyrlostTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class MainActivity: ComponentActivity() {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TyrlostTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val navController = rememberNavController()

                    val tierListsViewModel: MultipleTierListsViewModel = hiltViewModel<MultipleTierListsViewModel>()
                    val tierLists: List<TierListModel> by tierListsViewModel
                        .tierLists
                        .collectAsStateWithLifecycle()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.Main.route
                    ) {

                        composable(Screen.Main.route) {
                            MainComponent(
                                modifier = Modifier.padding(innerPadding),
                                navigateToTierList = { id -> navController.navigate(route = Screen.TierList.route(id)) },
                                createNewTierList = tierListsViewModel::addNewTierList,
                                deleteTier = tierListsViewModel::deleteTierList,
                                tierLists = tierLists
                            )
                        }

                        composable(
                            route = Screen.TierList.route,
                            arguments = listOf(navArgument("id"){
                                type = NavType.StringType
                            })
                        ) {

                            TierListComponent(
                                modifier = Modifier.padding(innerPadding),
                                id = it.arguments!!.getString("id")!!, //TODO ugly do not leave
                                navigateToMain = { navController.navigate(route = Screen.Main.route) }
                            )
                        }
                    }
                }
            }
        }
    }
}

