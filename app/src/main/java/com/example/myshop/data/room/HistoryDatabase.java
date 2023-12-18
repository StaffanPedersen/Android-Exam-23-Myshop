package com.example.myshop.data.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.myshop.data.CartProducts;
import com.example.myshop.data.History;
import com.example.myshop.data.room.HistoryDao;

@Database(entities = {CartProducts.class, History.class}, version = 2)
public abstract class HistoryDatabase extends RoomDatabase {
    public abstract com.example.myshop.data.room.ShoppingCartDao shoppingCartDao();
    public abstract HistoryDao historyDao();
}