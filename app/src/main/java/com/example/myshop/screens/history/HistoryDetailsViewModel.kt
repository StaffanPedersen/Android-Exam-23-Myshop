package com.example.myshop.screens.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myshop.data.HistoryRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class HistoryViewModel: ViewModel() {
    val historyItems = HistoryRepository.getAllHistory().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
}
