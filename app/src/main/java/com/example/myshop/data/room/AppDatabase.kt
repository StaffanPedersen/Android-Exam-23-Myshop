package com.example.myshop.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myshop.data.Products

@Database(
    entities = [Products::class],
    version = 14,
    exportSchema = false
)
@TypeConverters(CustomTypeConverter::class)
abstract class ShopDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao
}




