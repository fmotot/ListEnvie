package com.example.myapplication.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.myapplication.model.Item;
import com.example.myapplication.repository.IItemRepository;
import com.example.myapplication.repository.RepoFactory;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {

    private IItemRepository repo;

    private LiveData<List<Item>> observer = null;

    public ItemViewModel(@NonNull Application application) {
        super(application);
        repo = RepoFactory.getItemRepository(application);
        observer = repo.get();
    }

    public LiveData<List<Item>> get(){
        return observer;
    }

    void insert(Item item){
        repo.insert(item);
    }

    void update(Item item){
        repo.update(item);
    }

    void delete(Item item){
        repo.delete(item);
    }

    void delete(){
        repo.delete();
    }
}
