package com.example.myshop.data.dataClass

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cartProducts")
data class CartProducts(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    var quantity: Int,
)




