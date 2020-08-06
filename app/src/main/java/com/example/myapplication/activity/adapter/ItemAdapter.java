package com.example.myapplication.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.activity.ItemActivity;
import com.example.myapplication.activity.ListItemActivity;
import com.example.myapplication.activity.viewholder.ItemViewHolder;
import com.example.myapplication.model.Item;
import com.example.myapplication.repository.IItemRepository;
import com.example.myapplication.repository.RepoFactory;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private Context context;

    // For data
    private List<Item> items;

//    public ItemAdapter(@NonNull Context context, int resource, @NonNull List<Item> objects) {
//        super(context, resource, objects);
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//
//        if (convertView == null) {
//            LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = li.inflate(R.layout.style_item_list, parent, false);
//        }
//
//        ImageView purchase = (ImageView)convertView.findViewById(R.id.btn_check_item);
//
//        Item item = getItem(position);
//        purchase.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                Item item = (Item) parent.getAdapter().getItem(i);
//                Item item = getItem(position);
//
//                if (item != null) {
//
//                    item.setPurchased(!item.isPurchased());
//                    String phrase = item.isPurchased() ? "Article acheté" : "Article non acheté";
//                    Toast.makeText(context, phrase, Toast.LENGTH_LONG).show();
//                    IItemRepository repo = RepoFactory.getItemRepository(context);
//
//                    repo.update(item);
//                }
//            }
//        });
//
//        ((TextView)convertView.findViewById(R.id.tv_item_name)).setText(item.getName());
//        ((TextView)convertView.findViewById(R.id.tv_item_price)).setText(String.valueOf(item.getPrice()));
//        ((TextView)convertView.findViewById(R.id.tv_item_description)).setText(item.getDescription());
//        ((RatingBar)convertView.findViewById(R.id.rb_item_rating)).setRating(item.getRating());
//
//        if (item.isPurchased()){
//            ((ImageView) convertView.findViewById(R.id.btn_check_item)).setColorFilter(ContextCompat.getColor(context, R.color.colorAccent), android.graphics.PorterDuff.Mode.SRC_IN);
//        }
//        else {
//            ((ImageView) convertView.findViewById(R.id.btn_check_item)).setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN);
//        }
//
//        return convertView;
//    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /**
         * Create view holder and inflating its xml layout
         */
        context = parent.getContext();
        LayoutInflater li = LayoutInflater.from(context);
        View view = li.inflate(R.layout.style_item_list, parent, false);

        return new ItemViewHolder(view);
    }

    /**
     * Update view holder with an item
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.updateWithItem(this.items.get(position));
    }

    /**
     * return the total count of items in the list
     * @return
     */
    @Override
    public int getItemCount() {
        return this.items.size();
    }
}
