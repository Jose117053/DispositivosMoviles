package com.example.postresycafe.DataBase.CRUD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.postresycafe.DataBase.Entities.Order;
import com.example.postresycafe.DataBase.OperacionesGeneralesBD;

import java.util.ArrayList;

public class OrderDB extends OperacionesGeneralesBD<Order> {

    private static final String TABLE_NAME = "ORDERS";
    private static final String COL_ID = "idOrder";
    private static final String COL_USER_ID = "idUser";
    private static final String COL_TOTAL_PRICE = "total_price";

    public OrderDB(Context context) {
        super(context);
    }

    public long insertOrder(Order order) {
        ContentValues values = new ContentValues();
        values.put(COL_USER_ID, order.getIdUser());
        values.put(COL_TOTAL_PRICE, order.getTotalPrice());
        return insert(TABLE_NAME, values);
    }

    @Override
    protected Order cursorToEntity(Cursor c) {
        Order order = new Order();
        order.setIdOrder(c.getInt(c.getColumnIndexOrThrow(COL_ID)));
        order.setIdUser(c.getInt(c.getColumnIndexOrThrow(COL_USER_ID)));
        order.setTotalPrice(c.getDouble(c.getColumnIndexOrThrow(COL_TOTAL_PRICE)));
        return order;
    }

    @Override
    public ArrayList<Order> getAll() {
        ArrayList<Order> orders = new ArrayList<>();
        Cursor c = query(TABLE_NAME, new String[]{COL_ID, COL_USER_ID, COL_TOTAL_PRICE}, null, null, null);

        if (c != null) {
            while (c.moveToNext()) {
                orders.add(cursorToEntity(c));
            }
            c.close();
        }
        return orders;
    }
}