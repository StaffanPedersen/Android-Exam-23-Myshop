package com.example.myshop.data

import android.content.Context
import androidx.room.Room
import com.example.myshop.data.room.ShoppingCartDatabase
import kotlinx.coroutines.flow.Flow

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

    fun getAllCartProducts(): Flow<List<CartProducts>> = _shoppingCartDao.getAllCartProducts()

    suspend fun getCartProductById(id: Int): CartProducts = _shoppingCartDao.getCartProductById(id)

    suspend fun updateCartProduct(cartProduct: CartProducts) =
        _shoppingCartDao.updateCartProduct(cartProduct)

    suspend fun insertCartProducts(cartProducts: List<CartProducts>) =
        _shoppingCartDao.insertCartProducts(cartProducts)

    suspend fun removeProductFromCart(productId: Int) {
        _shoppingCartDao.deleteCartById(productId)
    }
    suspend fun clearCart() {
        // Use the DAO to clear the shopping cart database
        _shoppingCartDao.clearCart()
    }

}