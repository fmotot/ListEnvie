package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.R;
import com.example.myapplication.model.Item;
import com.example.myapplication.repository.IItemRepository;
import com.example.myapplication.repository.RepoFactory;
import com.example.myapplication.view_model.ItemViewModel;
import com.facebook.stetho.Stetho;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "=== INFO ===";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // to see DB in chrome
        Stetho.initializeWithDefaults(this);

        setContentView(R.layout.activity_main);

        IItemRepository repo = RepoFactory.getItemRepository(this);

        ItemViewModel ivm = ViewModelProviders.of(this).get(ItemViewModel.class);

        Log.i(TAG, "========================================================================================================");
        Log.i(TAG, "Test en cours");

        LiveData<List<Item>> observer = repo.get();
        observer.observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {

                for(Item item : items){
                    Log.i(TAG, item.toString());
                }
            }
        });

        repo.insert(new Item(0, "Pain au chocolat", 4, "http://www.boulangerie.com/pain-au-chocolat", "Miam miam", 1.1f, false));
    }
}