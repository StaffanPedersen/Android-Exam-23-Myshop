package com.example.myshop.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myshop.data.dataClass.History
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(history: History)

    @Query("SELECT * FROM history WHERE product_id = :productId")
    fun getHistory(productId: Int): Flow<List<History>>

    @Query("SELECT * FROM history")
    fun getAllHistory(): Flow<List<History>>
}