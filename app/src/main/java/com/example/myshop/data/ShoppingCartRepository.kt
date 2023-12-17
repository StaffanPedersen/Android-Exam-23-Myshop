package com.example.myshop.data

import android.content.Context
import androidx.room.Room
import com.example.myshop.data.room.ShoppingCartDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

object ShoppingCartRepository {

    private lateinit var _shoppingCartDatabase: ShoppingCartDatabase
    private val _shoppingCartDao by lazy { _shoppingCartDatabase.shoppingCartDao() }

    fun initializeDatabase(context: Context) {
        _shoppingCartDatabase = Room.databaseBuilder(
            context,
            ShoppingCartDatabase::class.java,
            "cart-database"
        ).fallbackToDestructiveMigration().build()
    }

    fun deleteCartById(productCartId: Int): Flow<Int> = flow {
        emit(_shoppingCartDao.deleteCartById(productCartId))
    }.flowOn(Dispatchers.IO)

    fun getAllCartProducts(): Flow<List<CartProducts>> = _shoppingCartDao.getAllCartProducts()

    suspend fun insertCartProducts(cartProducts: List<CartProducts>) =
        _shoppingCartDao.insertCartProducts(cartProducts)

}