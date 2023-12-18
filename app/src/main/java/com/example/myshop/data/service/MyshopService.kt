package com.example.myshop.data.service

import com.example.myshop.data.dataClass.Products
import retrofit2.Response
import retrofit2.http.GET

interface MyshopService {

    @GET("products/?limit=100")
    suspend fun getAllProducts(): Response <List<Products>>

}