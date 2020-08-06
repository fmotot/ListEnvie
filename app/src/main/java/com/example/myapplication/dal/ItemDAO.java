package com.example.myapplication.dal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.model.Item;

import java.util.List;

@Dao
public interface ItemDAO {

    @Insert
    void insert(Item item);

    @Insert
    void insert(Item... items);

    @Query("SELECT * FROM Item")
    LiveData<List<Item>> get();

    @Query("SELECT * FROM Item WHERE id = :id")
    Item get(int id);

    @Update
    void update(Item item);

    @Delete
    void delete(Item item);

    @Query("DELETE FROM Item")
    void delete();
}
