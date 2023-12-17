package com.example.myshop.data.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


import com.example.myshop.data.CartProducts;

@Database(entities = {CartProducts.class}, version = 1, exportSchema = false)
//@TypeConverters({CustomTypeConverter.class})
public abstract class ShoppingCartDatabase extends RoomDatabase {
    public abstract ShoppingCartDao shoppingCartDao();
}

