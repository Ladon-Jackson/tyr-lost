package com.example.tyrlost.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tyrlost.helpers.FileHelper
import com.example.tyrlost.models.TierListDao
import com.example.tyrlost.models.TierListModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@kotlinx.coroutines.ExperimentalCoroutinesApi
@HiltViewModel
class TierListsViewModel @Inject constructor(
    private val dao: TierListDao,
    private val fileService: FileHelper,
): ViewModel() {

    fun deleteAllImages() {
        fileService.deleteAllImages()
    }

    val tierLists: StateFlow<List<TierListModel>> = dao
        .getTierLists()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addNewTierList() = viewModelScope.launch { dao.upsertTierList() }

    fun deleteTierList(tierListModel: TierListModel) = viewModelScope.launch {
        fileService.deleteImagesById(tierListModel.id)
        dao.deleteTierList(tierListModel)
    }
}
