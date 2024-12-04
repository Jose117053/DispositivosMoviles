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
    public long add(Order order) {
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

    /**
     * Crea una nueva orden asociada a un usuario y productos en el carrito.
     * @param idUser     ID del usuario.
     * @param totalPrice Precio total de la orden.
     * @return ID de la orden creada.
     */
    public long createOrder(int idUser, double totalPrice) {
        Order order = new Order(idUser, totalPrice);
        return add(order);
    }
}
