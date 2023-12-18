package com.example.myshop.data.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.myshop.data.dataClass.CartProducts;
import com.example.myshop.data.dataClass.History;

@Database(entities = {CartProducts.class, History.class}, version = 2)
public abstract class HistoryDatabase extends RoomDatabase {

    public abstract HistoryDao historyDao();
}