package com.example.tyrlost

import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tyrlost.presentation.components.mainScreen.MainComponent
import com.example.tyrlost.presentation.components.tierListScreen.TierListComponent
import com.example.tyrlost.presentation.navigation.Screen
import com.example.tyrlost.ui.theme.TyrlostTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TyrlostTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

//                    TODO think this was just me testing out reading files can probs delete this at some point
//                    applicationContext.contentResolver.query(
//                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null
//                    )?.use { cursor ->
//                        val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
//                        while (cursor.moveToNext()) {
//                            val name = cursor.getString(nameColumn)
//                            println("THIS FILE NAME IS")
//                        }
//                    }

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.Main.route
                    ) {
                        composable(Screen.Main.route) {
                            MainComponent(
                                Modifier.padding(innerPadding),
                                navigateToTierList = { navController.navigate(route = Screen.TierList.route) }
                            )
                        }
                        composable(Screen.TierList.route) {
                            TierListComponent(
                                Modifier.padding(innerPadding),
                                navigateToMain = { navController.navigate(route = Screen.Main.route) }
                            )
                        }
                    }
                }
            }
        }
    }
}

