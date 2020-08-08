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

public class ItemViewModel extends AndroidViewModel {

    private IItemRepository repo;

    private LiveData<List<Item>> itemLiveData = null;
    private List<Item> itemList;

    public ItemViewModel(@NonNull Application application) {
        super(application);

        repo = RepoFactory.getItemRepository(application);

        init();

    }

    private void init() {
        itemLiveData = repo.get();
    }

    public LiveData<List<Item>> get(){

        if (itemLiveData == null){
            itemLiveData = new MutableLiveData<>();
            itemLiveData = repo.get();
        }
        else {
            repo.get();
        }

        return itemLiveData;
    }

    void insert(Item item){
        repo.insert(item);
    }

    void update(Item item){
        repo.update(item);
    }

    public void delete(Item item){
        repo.delete(item);
    }

    void delete(){
        repo.delete();
    }
}
