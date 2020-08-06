package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.model.Item;
import com.example.myapplication.repository.IItemRepository;
import com.example.myapplication.repository.RepoFactory;
import com.facebook.stetho.Stetho;

public class InsertItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_insert_item);
    }

    public void onClickSaveItem(View view) {

        IItemRepository repo = RepoFactory.getItemRepository(this);

        EditText et_name = findViewById(R.id.et_item_name);
        EditText et_description = findViewById(R.id.et_item_description);
        EditText et_price = findViewById(R.id.et_item_price);
        EditText et_link = findViewById(R.id.et_item_link);
        RatingBar rb_rating = findViewById(R.id.rb_item_rating);

        String name = et_name.getText().toString();
        String description = et_description.getText().toString();
        float price = Float.parseFloat(et_price.getText().toString());
        String link = et_link.getText().toString();
        int rating = (int) rb_rating.getRating();


        Item item = new Item(0, name, rating, link, description, price, false);
        repo.insert(item);

        Toast.makeText(this, "Article : " + name + " ajouté !", Toast.LENGTH_LONG).show();

        et_name.setText("");
        et_description.setText("");
        et_link.setText("");
        et_price.setText("");
        rb_rating.setRating(0);
    }
}