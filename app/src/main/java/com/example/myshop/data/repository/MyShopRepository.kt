package com.example.myshop.data.repository

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.myshop.data.dataClass.Products
import com.example.myshop.data.room.ShopDatabase
import com.example.myshop.data.service.MyshopService
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


object MyShopRepository {

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val retrofit = Retrofit.Builder()
        .client(httpClient)
        .baseUrl("https://fakestoreapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val _productService = retrofit.create(MyshopService::class.java)

    private lateinit var _shopDatabase: ShopDatabase
    private val _productDao by lazy { _shopDatabase.productDao() }

    fun initializeDatabase(context: Context) {
        _shopDatabase = Room.databaseBuilder(
            context,
            ShopDatabase::class.java,
            "shop-database"
        ).fallbackToDestructiveMigration().build()
    }

   fun getProduct(): Flow<List<Products>> = flow {
        val response = _productService.getAllProducts()
        if (response.isSuccessful) {
            response.body()?.let { products ->

                _productDao.insertProducts(products)
                emitAll(_productDao.getAllProducts())
            } ?: throw Exception("failed: Empty response body")
        } else {
            throw Exception("Response was not successful")
        }
    }.onStart {

        emit(_productDao.getAllProducts().first())
    }.catch { e ->

        Log.e("MyshopRepository", "Failed to get list of products", e)
        emitAll(_productDao.getAllProducts())
    }.flowOn(Dispatchers.IO)

    fun getProductById(productId: Int): Flow<Products?> {
        return _productDao.getProductById(productId).flowOn(Dispatchers.IO)
    }
}
