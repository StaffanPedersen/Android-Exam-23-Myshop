package com.example.myshop.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myshop.data.dataClass.Products
import kotlinx.coroutines.flow.Flow



@Dao
interface ProductDao {
    @Query("SELECT * FROM Products")
    fun getAllProducts(): Flow<List<Products>>

    @Query("SELECT * FROM Products WHERE id = :productId")
    fun getProductById(productId: Int): Flow<Products?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<Products>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProduct(product: Products)
}
