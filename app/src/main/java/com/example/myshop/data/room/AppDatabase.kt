package com.example.myshop.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myshop.data.Products

@Database(
    entities = [Products::class],
    version = 13,
    exportSchema = false
)
abstract class ShopDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao
}



