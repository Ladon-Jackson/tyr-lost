package com.example.tyrlost.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


class TierNameFieldViewModel: ViewModel() {

    val _text: MutableStateFlow<String> = MutableStateFlow("")
    val text: StateFlow<String> = _text

    fun updateText(newText: String) = _text.update { newText }

    fun resetText() = _text.update { "" }
}
