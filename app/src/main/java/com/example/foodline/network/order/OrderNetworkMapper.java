package com.example.foodline.network.order;

import com.example.foodline.model.Order;
import com.example.foodline.model.OrderItem;
import com.example.foodline.model.User;
import com.example.foodline.utils.EntityMapper;

import java.util.ArrayList;
import java.util.List;

public class OrderNetworkMapper implements EntityMapper<Order, OrderNetworkEntity> {
    @Override
    public Order mapFromEntity(OrderNetworkEntity orderNetworkEntity) {
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemNetworkEntity o: orderNetworkEntity.getOrderItems()){
            orderItems.add(new OrderItem(o.getOrderItemid(), o.getName(), o.getQty(), o.getPrice(), o.getImage()));
        }

        return new Order(orderNetworkEntity.getOrderid(), orderItems, new User(orderNetworkEntity.getUser().getUsername(), orderNetworkEntity.getUser().getEmail()),
                orderNetworkEntity.getPaymentMethod(), orderNetworkEntity.isIsAccepted(), orderNetworkEntity.getTaxPrice(), orderNetworkEntity.getTotalPrice());
    }

    @Override
    public OrderNetworkEntity mapToEntity(Order order) {
        return null;
    }

    @Override
    public List<Order> mapFromEntityList(List<OrderNetworkEntity> orderNetworkEntities) {
        List<Order> orders = new ArrayList<>();

        for (OrderNetworkEntity o: orderNetworkEntities) {
            orders.add(mapFromEntity(o));
        }
        return orders;
    }
}
