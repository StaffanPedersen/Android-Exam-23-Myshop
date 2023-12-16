package com.example.myshop.data

import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET

interface ShoppingCartService {
    @GET("products/?limit=100")
    suspend fun getAllCartProducts(): Response<List<CartProducts>>

    @DELETE  ("products/{id}/")
    suspend fun deleteCartById(
        id: Int
    ): Response<CartProducts>

}