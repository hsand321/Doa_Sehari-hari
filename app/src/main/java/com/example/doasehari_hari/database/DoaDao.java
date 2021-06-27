package com.example.doasehari_hari.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DoaDao {

    @Insert
    void insert(Doa doa);

    @Delete
    void delete(Doa doa);

    @Query("SELECT * FROM  doa_table ORDER BY title ASC")
    LiveData<List<Doa>>getAllDoa();
}
