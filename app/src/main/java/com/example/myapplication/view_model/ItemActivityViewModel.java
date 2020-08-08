package com.example.myapplication.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.model.Item;
import com.example.myapplication.repository.IItemRepository;
import com.example.myapplication.repository.RepoFactory;

import java.util.List;

public class ItemActivityViewModel extends AndroidViewModel {

    private IItemRepository repo;

    private LiveData<Item> itemObserver = null;

    public ItemActivityViewModel(@NonNull Application application) {
        super(application);
        repo = RepoFactory.getItemRepository(application);
    }

    public LiveData<Item> get(int id)
    {
        if(itemObserver == null)
        {
            itemObserver = new MutableLiveData<>();
            itemObserver = repo.get(id);
        }else {
            repo.get(id);
        }
        return itemObserver;
    }

    void insert(Item item){
        repo.insert(item);
    }

    public void update(Item item){
        repo.update(item);
    }

    void delete(Item item){
        repo.delete(item);
    }

    void delete(){
        repo.delete();
    }
}
