package com.example.myshop.data.room.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myshop.data.dataClass.CartProducts;
import com.example.myshop.data.room.Dao.ShoppingCartDao;

@Database(entities = {CartProducts.class}, version = 7, exportSchema = false)

public abstract class ShoppingCartDatabase extends RoomDatabase {
    public abstract ShoppingCartDao shoppingCartDao();
}

