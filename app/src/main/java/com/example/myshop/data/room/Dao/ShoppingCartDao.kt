package com.example.myshop.data.room.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myshop.data.dataClass.CartProducts
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingCartDao {
    @Query("SELECT * FROM CartProducts")
    fun getAllCartProducts(): Flow<List<CartProducts>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCartProduct(cartProduct: CartProducts)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartProducts(cartProducts: List<CartProducts>)

    @Query("SELECT * FROM CartProducts WHERE id = :id")
    suspend fun getCartProductById(id: Int): CartProducts

    @Query("DELETE FROM CartProducts WHERE id = :id")
    suspend fun deleteCartById(id: Int): Int

    @Query("DELETE FROM CartProducts")
    suspend fun clearCart()
}