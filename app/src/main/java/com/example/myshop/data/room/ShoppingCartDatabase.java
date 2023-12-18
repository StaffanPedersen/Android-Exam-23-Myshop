package com.example.myshop.data.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myshop.data.dataClass.CartProducts;

@Database(entities = {CartProducts.class}, version = 7, exportSchema = false)

public abstract class ShoppingCartDatabase extends RoomDatabase {
    public abstract ShoppingCartDao shoppingCartDao();
}

