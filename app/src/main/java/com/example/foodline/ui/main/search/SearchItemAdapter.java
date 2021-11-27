package com.example.foodline.ui.main.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodline.R;
import com.example.foodline.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class SearchItemAdapter extends RecyclerView.Adapter<SearchItemAdapter.SearchItemViewHolder> {

    private List<MenuItem> menuItems;
    private Context context;

    public SearchItemAdapter(){
        this.menuItems = new ArrayList<>();
    }

    @NonNull
    @Override
    public SearchItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new SearchItemViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_food_menu_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchItemViewHolder holder, int position) {
        holder.bind(menuItems.get(position));
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    class SearchItemViewHolder extends RecyclerView.ViewHolder{

        public SearchItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(MenuItem menuItem) {

        }
    }
}
