package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.activity.adapter.ItemRecyclerViewAdapter;
import com.example.myapplication.model.Item;
import com.example.myapplication.view_model.ItemViewModel;

import java.util.List;

public class ListItemActivity extends AppCompatActivity {

    public static final String KEY_ITEM = "item";

    private RecyclerView listRecyclerView;
    private ItemRecyclerViewAdapter recyclerViewAdapter;
    private ItemViewModel vm;
    private ListItemActivity context;

    private List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);

        context = this;
        listRecyclerView = findViewById(R.id.item_list);
        vm = ViewModelProviders.of(this).get(ItemViewModel.class);

        setItemAdapter();
    }

    /**
     * Fill the list with items using the adapter
     */
    private void setItemAdapter() {
        vm.get().observe(context, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                recyclerViewAdapter = new ItemRecyclerViewAdapter(context, items);
                recyclerViewAdapter.onAttachedToRecyclerView(listRecyclerView);
                listRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                listRecyclerView.setAdapter(recyclerViewAdapter);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void onClickAddItem(View view) {
        Intent intent = new Intent(ListItemActivity.this, InsertItemActivity.class);
        startActivity(intent);
    }
}