package com.example.myshop.data

import android.content.Context
import androidx.room.Room
import com.example.myshop.data.room.HistoryDatabase
import kotlinx.coroutines.flow.Flow

object HistoryRepository : HistoryService {
    private lateinit var db: HistoryDatabase
    private val historyDao by lazy { db.historyDao() }

    fun initializeDatabase(context: Context) {
        db = Room.databaseBuilder(
            context,
            HistoryDatabase::class.java,
            "history-database"
        ).fallbackToDestructiveMigration().build()
    }

    override suspend fun insertHistory(history: History) {
        historyDao.insertHistory(history)
    }

    override fun getHistory(productId: Int): Flow<List<History>> {
        return historyDao.getHistory(productId)
    }

    fun getAllHistory(): Flow<List<History>> {
        return historyDao.getAllHistory()
    }
}