package com.example.foodline.ui;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
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

    List<MenuItem> menuItems = new ArrayList<>();

    @NonNull
    @Override
    public MenuItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_menu_item_layout, parent, false);
        return new MenuItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemViewHolder holder, int position) {
        MenuItem menuItem = menuItems.get(position);

        holder.menuItemImage.setImageResource(R.drawable.ic_logo);
        holder.menuItemName.setText(menuItem.getName());
        holder.menuItemPrice.setText(menuItem.getPrice());

        holder.addIconBtn.setOnClickListener(v -> changeState(holder));
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public void submitList(List<MenuItem> listData){
        menuItems.clear();
        menuItems.addAll(listData);
        notifyDataSetChanged();
    }

    private void changeState(MenuItemViewHolder holder) {

        LottieAnimationView toggle = holder.addIconBtn;

        if (holder.flag == 0) {
            toggle.setSpeed(3f);
            toggle.playAnimation();
            Drawable[] drawables = {ContextCompat.getDrawable(toggle.getContext(), R.drawable.ic_add_lottie_back),ContextCompat.getDrawable(toggle.getContext(), R.drawable.ic_add_lottie_selected_back)};
            TransitionDrawable transitionDrawable = new TransitionDrawable(drawables);
            toggle.setBackground(transitionDrawable);
            transitionDrawable.startTransition(400);
            holder.flag = 1;
        } else {
            toggle.setSpeed(-3f);
            toggle.playAnimation();
            Drawable[] drawables = {ContextCompat.getDrawable(toggle.getContext(), R.drawable.ic_add_lottie_selected_back),ContextCompat.getDrawable(toggle.getContext(), R.drawable.ic_add_lottie_back)};
            TransitionDrawable transitionDrawable = new TransitionDrawable(drawables);
            toggle.setBackground(transitionDrawable);
            transitionDrawable.startTransition(400);
            holder.flag = 0;
        }
    }

    class MenuItemViewHolder extends RecyclerView.ViewHolder{

        private int flag;
        private ImageView menuItemImage;
        private TextView menuItemName;
        private TextView menuItemPrice;
        private LottieAnimationView addIconBtn;

        public MenuItemViewHolder(@NonNull View itemView) {
            super(itemView);
            flag = 0;
            menuItemImage = itemView.findViewById(R.id.menu_item_image);
            menuItemName = itemView.findViewById(R.id.menu_item_name);
            menuItemPrice = itemView.findViewById(R.id.menu_item_price);
            addIconBtn = itemView.findViewById(R.id.add_icon);
        }
    }
}
