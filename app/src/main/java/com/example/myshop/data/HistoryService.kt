package com.example.myshop.data

import kotlinx.coroutines.flow.Flow

interface HistoryService {
    suspend fun insertHistory(history: History)
    fun getHistory(productId: Int): Flow<List<History>>
}