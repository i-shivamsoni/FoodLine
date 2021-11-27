package com.example.foodline.ui.admin.notifications;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodline.R;
import com.example.foodline.model.Order;
import com.example.foodline.model.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.OrderItemViewHolder> {

    private List<Order> orders;
    private Context context;
    private MyAdapterListener listener;

    public OrderItemAdapter(MyAdapterListener listener) {
        this.listener = listener;
        this.orders = new ArrayList<>();
    }

    @NonNull
    @Override
    public OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.layout_order_item, parent, false);
        return new OrderItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemViewHolder holder, int position) {
        holder.bindView(orders.get(position));
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public void submitList(List<Order> newOrders) {
        orders = newOrders;
        notifyDataSetChanged();
    }

    class OrderItemViewHolder extends RecyclerView.ViewHolder{

        TextView nameText;
        TextView itemsText;
        TextView totalPriceText;
        Button acceptOrder;

        public OrderItemViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.name_text);
            itemsText = itemView.findViewById(R.id.items_text);
            totalPriceText = itemView.findViewById(R.id.price_text);
            acceptOrder = itemView.findViewById(R.id.accept_btn);
        }

        public void bindView(Order order) {
            nameText.setText(order.getUser().getUsername());

            StringBuilder orderItems = new StringBuilder();
            List<OrderItem> orderItemList = order.getOrderItems();

            for (int i = 0; i < orderItemList.size(); i++) {
                if(i == (orderItemList.size() - 1)) {
                    orderItems.append(orderItemList.get(i).getQuantity()).append(" ").append(orderItemList.get(i).getOrderName());
                    break;
                }
                orderItems.append(orderItemList.get(i).getQuantity()).append(" ").append(orderItemList.get(i).getOrderName()).append(", ");
            }

            itemsText.setText(orderItems);
            totalPriceText.setText(order.getTotalPrice());

            acceptOrder.setOnClickListener(v -> listener.onAcceptBtnClicked(String.valueOf(order.getOrderId())));
        }
    }

    interface MyAdapterListener {
        void onAcceptBtnClicked(String id);
    }
}
