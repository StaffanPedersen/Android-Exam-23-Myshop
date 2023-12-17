package com.example.myshop.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cartProducts")
data class CartProducts(
    @PrimaryKey (autoGenerate = true)
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    // Uncomment the following lines if you have a TypeConverter for the Rating class
    // val rating: Rating,
) {

}

// Uncomment the following lines if you want to use a Rating class
// data class Rating(
//     val rate: Double,
//     val count: Int
// )