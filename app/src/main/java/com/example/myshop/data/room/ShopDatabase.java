package com.example.myshop.data.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.example.myshop.data.Products;

@Database(entities = {Products.class}, version = 15, exportSchema = false)
@TypeConverters({CustomTypeConverter.class})
public abstract class ShopDatabase extends RoomDatabase {
    public abstract ProductDao productDao();
}