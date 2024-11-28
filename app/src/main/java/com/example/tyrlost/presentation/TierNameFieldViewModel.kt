package com.example.tyrlost.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update


class TierNameFieldViewModel: ViewModel() {

    val text: MutableStateFlow<String> = MutableStateFlow("")

    fun updateText(newText: String) = text.update { newText }

    fun resetText() = text.update { "" }
}
