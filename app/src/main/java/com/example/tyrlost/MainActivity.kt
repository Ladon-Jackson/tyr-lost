package com.example.tyrlost

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import com.example.tyrlost.presentation.components.ButtonsComponent
import com.example.tyrlost.presentation.components.TierListComponent
import com.example.tyrlost.presentation.models.testTiers
import com.example.tyrlost.ui.theme.TyrlostTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TyrlostTheme {
                Column {
                    TierListComponent()
                    ButtonsComponent()
                }
            }
        }
    }
}

