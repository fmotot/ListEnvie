package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.R;
import com.example.myapplication.activity.adapter.ItemRecyclerViewAdapter;
import com.example.myapplication.model.Item;
import com.example.myapplication.view_model.ItemActivityViewModel;

public class ItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        ItemActivityViewModel vm = ViewModelProviders.of(this).get(ItemActivityViewModel.class);
        Item item = null;

        Intent intent = getIntent();
        if (intent != null) {
            item = intent.getParcelableExtra(ItemRecyclerViewAdapter.KEY_ITEM);
        }

        LiveData<Item> itemObserver = vm.get(item.getId());

        itemObserver.observe(this, new Observer<Item>() {
            @Override
            public void onChanged(Item item) {
                ((TextView) ItemActivity.this.findViewById(R.id.et_item_name)).setText(item.getName());
                ((TextView) ItemActivity.this.findViewById(R.id.et_item_price)).setText(String.valueOf(item.getPrice()));
                ((TextView) ItemActivity.this.findViewById(R.id.et_item_link)).setText(item.getLink());
                ((TextView) ItemActivity.this.findViewById(R.id.et_item_description)).setText(item.getDescription());
                ((RatingBar) ItemActivity.this.findViewById(R.id.rb_item_rating)).setRating(item.getRating());
            }
        });

        itemObserver = vm.get(item.getId());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}