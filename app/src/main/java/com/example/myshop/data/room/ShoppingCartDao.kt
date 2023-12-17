package com.example.myshop.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myshop.data.CartProducts
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingCartDao {
    @Query("SELECT * FROM CartProducts")
    fun getAllCartProducts(): Flow<List<CartProducts>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartProducts(cartProducts: List<CartProducts>)

    @Query("DELETE FROM CartProducts WHERE id = :id")
    suspend fun deleteCartById(id: Int): Int
}