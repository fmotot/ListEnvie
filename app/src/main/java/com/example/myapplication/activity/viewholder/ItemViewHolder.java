package com.example.myapplication.activity.viewholder;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Item;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    private TextView tv_name;
    private TextView tv_price;
    private TextView tv_description;
    private RatingBar rb_rating;
    private ImageView btn_check;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);

        tv_name = itemView.findViewById(R.id.tv_item_name);
        tv_price = itemView.findViewById(R.id.tv_item_price);
        tv_description = itemView.findViewById(R.id.tv_item_description);
        rb_rating = itemView.findViewById(R.id.rb_item_rating);
        btn_check = itemView.findViewById(R.id.btn_check_item);
    }

    public void updateWithItem(final Item item) {

        Log.i("========== ItemViewHolder ============", item.toString());

        this.tv_name.setText(item.getName());
        this.tv_price.setText(String.valueOf(item.getPrice()));
        this.tv_description.setText(item.getDescription());
        this.rb_rating.setRating(item.getRating());

        if (item.isPurchased()) {
            this.btn_check.setColorFilter(ContextCompat.getColor(itemView.getContext(), R.color.colorAccent), android.graphics.PorterDuff.Mode.SRC_IN);
        } else {
            this.btn_check.setColorFilter(ContextCompat.getColor(itemView.getContext(), R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN);
        }
    }
}
