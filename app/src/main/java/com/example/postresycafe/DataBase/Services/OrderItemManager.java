package com.example.postresycafe.DataBase.Services;

import android.content.Context;

import com.example.postresycafe.DataBase.CRUD.OrderItemDB;
import com.example.postresycafe.DataBase.Entities.OrderItem;

import java.util.ArrayList;

public class OrderItemManager implements EntityManager<OrderItem> {

    private final OrderItemDB orderItemDB;

    public OrderItemManager(Context context) {
        this.orderItemDB = new OrderItemDB(context);
    }

    @Override
    public long add(OrderItem orderItem) {
        orderItemDB.openForWrite();
        long result = orderItemDB.insertOrderItem(orderItem);
        orderItemDB.close();
        return result;
    }

    @Override
    public ArrayList<OrderItem> getAll() {
        orderItemDB.openForRead();
        ArrayList<OrderItem> orderItems = orderItemDB.getAll();
        orderItemDB.close();
        return orderItems;
    }

    @Override
    public OrderItem getById(int id) {
        throw new UnsupportedOperationException("No se puede obtener un OrderItem por ID directamente.");
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Eliminación de OrderItems no implementada.");
    }

    @Override
    public boolean update(OrderItem orderItem) {
        throw new UnsupportedOperationException("Actualización de OrderItems no implementada.");
    }
}
