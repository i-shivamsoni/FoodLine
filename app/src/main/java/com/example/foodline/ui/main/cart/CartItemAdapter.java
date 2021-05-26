package com.example.foodline.ui.main.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.foodline.R;
import com.example.foodline.model.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder> {

    private List<MenuItem> cartItems;
    private CartItemListener cartItemListener;

    public CartItemAdapter(CartItemListener cartItemListener){
        this.cartItems = new ArrayList<MenuItem>();
        this.cartItemListener = cartItemListener;
    }

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cart_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        holder.bind(cartItems.get(position));
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public void submitList(List<MenuItem> cartList) {
        cartItems = cartList;
        notifyDataSetChanged();
    }

    class CartItemViewHolder extends RecyclerView.ViewHolder{

        private TextView cartItemName;
        private TextView cartItemPrice;
        private ElegantNumberButton addRemoveBtn;

        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.cartItemName = itemView.findViewById(R.id.cart_item_name);
            this.cartItemPrice = itemView.findViewById(R.id.cart_item_price);
            this.addRemoveBtn = itemView.findViewById(R.id.add_remove_btn);
        }

        public void bind(MenuItem menuItem) {
            cartItemName.setText(menuItem.getName());
            cartItemPrice.setText(String.format("Rs. %s", menuItem.getPrice()));
            addRemoveBtn.setNumber(String.valueOf(menuItem.getCounterInCart()));
            addRemoveBtn.setOnClickListener((ElegantNumberButton.OnClickListener) v -> cartItemListener.onClick((ElegantNumberButton)v, menuItem));
        }
    }

    public interface CartItemListener{
        void onClick(ElegantNumberButton view, MenuItem menuItem);
    }
}
