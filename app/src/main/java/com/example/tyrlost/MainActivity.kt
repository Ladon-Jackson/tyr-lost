package com.example.tyrlost

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.tyrlost.presentation.components.tierList.TierListComponent
import com.example.tyrlost.ui.theme.TyrlostTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TyrlostTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TierListComponent(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

