package com.example.myshop.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MyshopService {

    @GET("products/?limit=100")
    suspend fun getAllProducts(): Response <List<Products>>

    @GET("products/{id}/")
    suspend fun getProductById(
        @Path("id") id: Int
    ): Response<Products>
}