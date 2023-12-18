package com.example.myshop.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class History(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "shared_id") val sharedId: String,
    @ColumnInfo(name = "product_id") val productId: Int,
    @ColumnInfo(name = "purchase_date") val purchaseDate: String,
    @ColumnInfo(name = "product_title") val productTitle: String,
    @ColumnInfo(name = "product_quantity") val quantity: Int,
    @ColumnInfo(name = "product_price") val productPrice: Double,
 // @ColumnInfo(name = "product_description") val productDescription: String,
    @ColumnInfo(name = "product_category") val productCategory: String,
) {

}