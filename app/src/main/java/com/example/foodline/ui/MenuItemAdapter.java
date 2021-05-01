package com.example.foodline.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodline.R;
import com.example.foodline.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.MenuItemViewHolder> {

    List<MenuItem> menuItems;
    MyAdapterListener listener;
    Context context;

    public MenuItemAdapter(List<MenuItem> listData, MyAdapterListener listener){
        this.menuItems = listData;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MenuItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.food_menu_item_layout, parent, false);
        return new MenuItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemViewHolder holder, int position) {
        holder.bindView(menuItems.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d("tzuyu",String.valueOf(menuItems.size()));
        return menuItems.size();
    }

    public void submitList(List<MenuItem> listData){
        menuItems.addAll(listData);
        notifyDataSetChanged();
    }


    class MenuItemViewHolder extends RecyclerView.ViewHolder{

        private ImageView menuItemImage;
        private TextView menuItemName;
        private TextView menuItemPrice;
        private LottieAnimationView addIconBtn;

        public MenuItemViewHolder(@NonNull View itemView) {
            super(itemView);
            menuItemImage = itemView.findViewById(R.id.menu_item_image);
            menuItemName = itemView.findViewById(R.id.menu_item_name);
            menuItemPrice = itemView.findViewById(R.id.menu_item_price);
            addIconBtn = itemView.findViewById(R.id.add_icon_btn);
        }

        private void bindView(MenuItem menuItem){
            menuItemImage.setImageResource(R.drawable.ic_logo);
            menuItemName.setText(menuItem.getName());
            menuItemPrice.setText(menuItem.getPrice());

            if(menuItem.getInCart() == 0){
                addIconBtn.cancelAnimation();
                addIconBtn.setProgress(0f);
                addIconBtn.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_add_lottie_back));
            }else{
                addIconBtn.setProgress(1f);
                addIconBtn.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_add_lottie_selected_back));
            }

            addIconBtn.setOnClickListener(v -> listener.onAddBtnClicked(v, getAdapterPosition()));
        }

    }

    public interface MyAdapterListener{
        void onAddBtnClicked(View view, int position);
        void onMenuItemClicked(View view, int position);
    }
}
