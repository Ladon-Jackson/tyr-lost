package com.example.tyrlost.presentation.viewModels

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


class TierDetailsViewModel : ViewModel() {

    private val _text: MutableStateFlow<String> = MutableStateFlow("")
    val text: StateFlow<String> = _text

    private val _color: MutableStateFlow<Color> = MutableStateFlow(Color.White)
    val color: StateFlow<Color> = _color

    private val _colorsOpen: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val colorsOpen: StateFlow<Boolean> = _colorsOpen

    fun updateText(newText: String) = _text.update { newText }

    fun updateColor(newColor: Color) = _color.update { newColor }

    fun setColorsOpen(isOpen: Boolean) = _colorsOpen.update { isOpen }

    fun onReset() {
        _text.update { "" }
//        _color.update { "" }
    }
}
