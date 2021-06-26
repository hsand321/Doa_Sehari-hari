package com.example.doasehari_hari;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Doa.class}, version = 1)
public abstract class DoaDatabase extends RoomDatabase {

    private static DoaDatabase instance;
    public abstract DoaDao doaDao();
    public  static synchronized  DoaDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    DoaDatabase.class, "doa_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
