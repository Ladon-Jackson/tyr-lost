package com.example.tyrlost.presentation.viewModels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


class TierListDetailsViewModel : ViewModel() {

    private val _text: MutableStateFlow<String> = MutableStateFlow("")
    val text: StateFlow<String> = _text

    fun updateText(newText: String) = _text.update { newText }

    fun onReset() {
        _text.update { "" }
    }
}
