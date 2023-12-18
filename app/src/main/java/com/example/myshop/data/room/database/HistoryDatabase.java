package com.example.myshop.data.room.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.myshop.data.dataClass.History;
import com.example.myshop.data.room.Dao.HistoryDao;

@Database(entities = {History.class}, version = 2)
public abstract class HistoryDatabase extends RoomDatabase {

    public abstract HistoryDao historyDao();
}