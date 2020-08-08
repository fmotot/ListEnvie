package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private ItemActivityViewModel vm;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        vm = ViewModelProviders.of(this).get(ItemActivityViewModel.class);

        Intent intent = getIntent();
        if (intent != null) {
            item = intent.getParcelableExtra(ItemRecyclerViewAdapter.KEY_ITEM);
            displayItem(item);
            onClickSave();
        }
    }

    private void onClickSave() {
       Button btnUpdate = this.findViewById(R.id.btn_update_item);
       btnUpdate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               item.setName(((TextView) ItemActivity.this.findViewById(R.id.et_item_name)).getText().toString());
               item.setPrice(Float.parseFloat(((TextView) ItemActivity.this.findViewById(R.id.et_item_price)).getText().toString()));
               item.setLink(((TextView) ItemActivity.this.findViewById(R.id.et_item_link)).getText().toString());
               item.setDescription(((TextView) ItemActivity.this.findViewById(R.id.et_item_description)).getText().toString());
               item.setRating((int)((RatingBar) ItemActivity.this.findViewById(R.id.rb_item_rating)).getRating());

               vm.update(item);
               finish();
           }
       });
    }

    private void displayItem(Item item) {
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
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}