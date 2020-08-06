package com.example.myapplication.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.activity.ItemActivity;
import com.example.myapplication.model.Item;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {
    public ItemAdapter(@NonNull Context context, int resource, @NonNull List<Item> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.style_item_list, parent, false);
        }

        Item item = getItem(position);
        ((TextView)convertView.findViewById(R.id.tv_item_name)).setText(item.getName());
        ((TextView)convertView.findViewById(R.id.tv_item_price)).setText(String.valueOf(item.getPrice()));
        ((TextView)convertView.findViewById(R.id.tv_item_description)).setText(item.getDescription());
        ((RatingBar)convertView.findViewById(R.id.rb_item_rating)).setRating(item.getRating());

        return convertView;
    }
}
