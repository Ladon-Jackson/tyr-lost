package com.example.tyrlost.presentation.viewModels

import android.net.Uri
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


class CurrentSelectionViewModel : ViewModel() {

    private val _currentTierOpen: MutableStateFlow<Int?> = MutableStateFlow(null)
    private val _currentImageSelected: MutableStateFlow<Uri?> = MutableStateFlow(null)

    val currentTierOpen: StateFlow<Int?> = _currentTierOpen
    val currentImageSelected: StateFlow<Uri?> = _currentImageSelected

    fun openTierDialog(openTierIndex: Int) = _currentTierOpen.update { openTierIndex }
    fun closeTierDialog() = _currentTierOpen.update { null }

    fun updateImageSelected(updatedImage: Uri) = _currentImageSelected.update {
        if (it != null) null
        else updatedImage
    }
}
