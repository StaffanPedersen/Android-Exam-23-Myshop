package com.example.myshop.data

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.myshop.data.room.ShoppingCartDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ShoppingCartRepository {


    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val retrofit = Retrofit.Builder()
        .client(httpClient)
        //local database named ShoppingCartDatabase

        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val _cartService = retrofit.create(ShoppingCartService::class.java)

    private lateinit var _shoppingCartDatabase: ShoppingCartDatabase
    private val _shoppingCartDao by lazy { _shoppingCartDatabase.shoppingCartDao() }

    fun initializeDatabase(context: Context) {
        _shoppingCartDatabase = Room.databaseBuilder(
            context,
            ShoppingCartDatabase::class.java,
            "cart-database"
        ).fallbackToDestructiveMigration().build()
    }

    fun getCartProducts(): Flow<List<CartProducts>> = flow {
        val response = _cartService.getAllCartProducts()
        if (response.isSuccessful) {
            response.body()?.let { cartProducts ->

                _shoppingCartDao.insertCartProducts(cartProducts)
                emitAll(_shoppingCartDao.getAllCartProducts()) // Stream the new list from the database
            } ?: throw Exception("failed: Empty response body")
        } else {
            throw Exception("Response was not successful")
        }
    }.onStart {
        // Emit current data before fetching
        emit(_shoppingCartDao.getAllCartProducts().first())
    }.catch { e ->
        // Log the error and fallback to local data
        Log.e("ShoppingCartRepository", "Failed to get list of shopping cart products", e)
        emitAll(_shoppingCartDao.getAllCartProducts()) // Continue streaming from the local data
    }.flowOn(Dispatchers.IO) // Use the IO dispatcher for database and network operations

    fun deleteCartById(productCartId: Int): Flow<Int> = flow {
        emit(_shoppingCartDao.deleteCartById(productCartId))
    }.flowOn(Dispatchers.IO)


//    fun getCartProductById(productId: Int): Flow<CartProducts?> {
//        return _shoppingCartDao.getCartProductById(productId).flowOn(Dispatchers.IO)
//    }

//    suspend fun updateCartProduct(cartProducts: CartProducts) {
//        _shoppingCartDao.updateCartProduct(cartProducts)
//    }
}