package com.example.tyrlost.presentation.viewModels

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.em
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


//TODO probs remove this
class ResizingTextViewModel : ViewModel() {

    private val _currentSize: MutableStateFlow<TextUnit> = MutableStateFlow(10.em)
    val currentSize: StateFlow<TextUnit> = _currentSize

    fun setCurrentSize(newSize: TextUnit) = _currentSize.update { newSize }
    fun shrinkCurrentSize() = _currentSize.update { it * 0.95 }
}
