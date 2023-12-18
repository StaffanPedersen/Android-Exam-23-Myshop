package com.example.myshop.data.service

import com.example.myshop.data.dataClass.History
import kotlinx.coroutines.flow.Flow

interface HistoryService {
    suspend fun insertHistory(history: History)
    fun getHistory(productId: Int): Flow<List<History>>
}