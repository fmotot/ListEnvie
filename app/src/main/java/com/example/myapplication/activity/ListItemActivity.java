package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.activity.adapter.ItemAdapter;
import com.example.myapplication.model.Item;
import com.example.myapplication.view_model.ItemViewModel;

import java.util.List;

public class ListItemActivity extends AppCompatActivity {

    public static final String KEY_ITEM = "item";

    private ListView list = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);

        list = findViewById(R.id.item_list);
                Toast.makeText(ListItemActivity.this, "YOUHOUOU !!!", Toast.LENGTH_LONG).show();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Item item = (Item)list.getAdapter().getItem(i);


                if (item != null){
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getLink()));
                    startActivity(browserIntent);
                }
            }
        });

//        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//
//
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Item item = (Item)list.getAdapter().getItem(i);
//
//                if (item != null){
//                    Intent intent = new Intent(ListItemActivity.this, ItemActivity.class);
//                    intent.putExtra(KEY_ITEM, item);
//                    startActivity(intent);
//                }
//                return true;
//            }
//        });

        ItemViewModel vm = ViewModelProviders.of(this).get(ItemViewModel.class);

        LiveData<List<Item>> observer = vm.get();
        observer.observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {

                ItemAdapter adapter = new ItemAdapter(ListItemActivity.this, R.layout.style_item_list, items);
                list.setAdapter(adapter);
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