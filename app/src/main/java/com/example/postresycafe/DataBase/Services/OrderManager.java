package com.example.postresycafe.DataBase.Services;

import android.content.Context;

import com.example.postresycafe.DataBase.CRUD.OrderDB;
import com.example.postresycafe.DataBase.Entities.Order;

import java.util.ArrayList;

public class OrderManager implements EntityManager<Order> {

    private final OrderDB orderDB;

    public OrderManager(Context context) {
        this.orderDB = new OrderDB(context);
    }

    @Override
    public long insert(Order order) {
        orderDB.openForWrite();
        long result = orderDB.insertOrder(order);
        orderDB.close();
        return result;
    }

    @Override
    public ArrayList<Order> getAll() {
        orderDB.openForRead();
        ArrayList<Order> orders = orderDB.getAll();
        orderDB.close();
        return orders;
    }

    @Override
    public Order getById(int id) {
        orderDB.openForRead();
        ArrayList<Order> orders = orderDB.getAll();
        orderDB.close();
        for (Order order : orders) {
            if (order.getIdOrder() == id) {
                return order;
            }
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        orderDB.openForWrite();
        boolean result = orderDB.delete("ORDERS", "idOrder = ?", new String[]{String.valueOf(id)}) > 0;
        orderDB.close();
        return result;
    }

    @Override
    public boolean update(Order order) {
        return false;
    }

    public ArrayList<Order> getOrdersByUserId(int userId) {
        orderDB.openForRead();
        ArrayList<Order> orders = orderDB.getOrdersByUserId(userId);
        orderDB.close();
        return orders;
    }


}
