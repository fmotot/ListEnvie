package com.example.myapplication.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.model.Item;

import java.util.List;

/**
 * Interface to implement the DAO design pattern
 */
public interface IItemRepository {
    void insert(Item item);

    void insert(Item... items);

    LiveData<List<Item>> get();

    LiveData<Item> get(int id);

    void update(Item item);

    void delete(Item item);

    void delete();
}
