package com.example.myapplication.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.dal.AppDatabase;
import com.example.myapplication.dal.ItemDAO;
import com.example.myapplication.model.Item;

import java.util.List;

public class ItemDBRepository implements IItemRepository {

    private final ItemDAO itemDAO;
    private MutableLiveData<Item> observerOne = new MutableLiveData<>();

    public ItemDBRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        itemDAO = db.getItemDAO();
    }

    @Override
    public void insert(Item item) {
        new AsyncTask<Item, Void, Void>(){

            @Override
            protected Void doInBackground(Item... items) {
                itemDAO.insert(items[0]);
                return null;
            }
        }.execute(item);
    }

    @Override
    public void insert(Item... items) {
        new AsyncTask<Item, Void, Void>(){

            @Override
            protected Void doInBackground(Item... items) {
                itemDAO.insert(items);
                return null;
            }
        }.execute(items);
    }


    @Override
    public LiveData<List<Item>> get() {
        return itemDAO.get();
    }


    @Override
    public LiveData<Item> get(int id) {
        Log.i("===========================", "ERREUR ITEM ?");
        new AsyncTask<Integer, Void, Item>(){

            @Override
            protected Item doInBackground(Integer... integers) {

                return itemDAO.get(integers[0]);
            }

            @Override
            protected void onPostExecute(Item item) {
                super.onPostExecute(item);
                Log.i("ACOS","Modification de l'observateur avec " + item.toString());
                observerOne.setValue(item);
                Log.i("ACOS","Modification 2 de l'observateur avec " + observerOne.getValue().toString());
            }
        }.execute(id);
        return observerOne;
    }

    @Override
    public void update(Item item) {
        new AsyncTask<Item, Void, Void>(){

            @Override
            protected Void doInBackground(Item... items) {
                itemDAO.update(items[0]);
                return null;
            }
        }.execute(item);
    }

    @Override
    public void delete(Item item) {
        new AsyncTask<Item, Void, Void>(){

            @Override
            protected Void doInBackground(Item... items) {
                itemDAO.delete(items[0]);
                return null;
            }
        }.execute(item);
    }

    @Override
    public void delete() {
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                itemDAO.delete();
                return null;
            }
        }.execute();
    }
}
