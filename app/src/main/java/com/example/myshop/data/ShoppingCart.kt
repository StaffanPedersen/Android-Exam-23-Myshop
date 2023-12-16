package com.example.myshop.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cartProducts")
data class CartProducts(
    @PrimaryKey
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
//    val rating: Rating,
)

//data class Rating(
//    val rate: Double,
//    val count: Int
//)
