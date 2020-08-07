package com.example.myapplication.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.activity.ItemActivity;
import com.example.myapplication.activity.viewholder.ItemViewHolder;
import com.example.myapplication.model.Item;
import com.example.myapplication.repository.IItemRepository;
import com.example.myapplication.repository.RepoFactory;

import java.util.List;

public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    public static String KEY_ITEM = "key_item";

    private Context context;
    private View view;

    // For data
    private List<Item> items;

    public ItemRecyclerViewAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /**
         * Create view holder and inflating its xml layout
         */
        view = LayoutInflater.from(context).inflate(R.layout.style_item_list, parent, false);

        return new ItemViewHolder(view);
    }

    /**
     * Update view holder with an item
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Log.i("========== ItemRecyclerViewAdapter.onBindViewHolder ============", items.get(position).toString());
        final Item item = items.get(position);
        holder.updateWithItem(item);

        ImageView purchase = (ImageView) view.findViewById(R.id.btn_check_item);
        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (item != null) {

                    item.setPurchased(!item.isPurchased());
                    String phrase = item.isPurchased() ? "Article acheté" : "Article non acheté";
                    Toast.makeText(context, phrase, Toast.LENGTH_LONG).show();
                    IItemRepository repo = RepoFactory.getItemRepository(context);

                    repo.update(item);
                }
            }
        });

        /**
         * Open link on browser on click
         */
        view.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = item.getLink();
                Uri webpage = null;

                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    webpage = Uri.parse("http://" + url);
                } else {
                    webpage = Uri.parse(url);
                }

                if (url != null) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, webpage);
                    context.startActivity(browserIntent);
                }
            }
        });

        /**
         * Modifying activity on long click
         */
        view.setOnLongClickListener(new AdapterView.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                if (item != null) {
                    Intent intent = new Intent(context, ItemActivity.class);
                    intent.putExtra(KEY_ITEM, item);
                    context.startActivity(intent);
                }
                return true;
            }
        });
    }

    /**
     * return the total count of items in the list
     *
     * @return
     */
    @Override
    public int getItemCount() {
        Log.i("========== ItemRecyclerViewAdapter.GetItemCount ============", String.valueOf(this.items.size()));
        return this.items.size();
    }
}
