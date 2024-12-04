package com.example.postresycafe.DataBase.CRUD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.postresycafe.DataBase.Entities.OrderItem;

import java.util.ArrayList;

public class OrderItemDB extends GeneralOperationsDB<OrderItem> {

    private static final String TABLE_NAME = "OrderItems";
    private static final String COL_ORDER_ID = "idOrder";
    private static final String COL_PRODUCT_ID = "idProduct";
    private static final String COL_QUANTITY = "quantity";
    private static final String COL_LOCAL_PRICE = "local_Price";

    public OrderItemDB(Context context) {
        super(context);
    }

    public long insertOrderItem(OrderItem orderItem) {
        ContentValues values = new ContentValues();
        values.put(COL_ORDER_ID, orderItem.getIdOrder());
        values.put(COL_PRODUCT_ID, orderItem.getIdProduct());
        values.put(COL_QUANTITY, orderItem.getQuantity());
        values.put(COL_LOCAL_PRICE, orderItem.getLocalPrice());
        return insert(TABLE_NAME, values);
    }

    @Override
    protected OrderItem cursorToEntity(Cursor c) {
        OrderItem orderItem = new OrderItem();
        orderItem.setIdOrder(c.getInt(c.getColumnIndexOrThrow(COL_ORDER_ID)));
        orderItem.setIdProduct(c.getInt(c.getColumnIndexOrThrow(COL_PRODUCT_ID)));
        orderItem.setQuantity(c.getInt(c.getColumnIndexOrThrow(COL_QUANTITY)));
        orderItem.setLocalPrice(c.getDouble(c.getColumnIndexOrThrow(COL_LOCAL_PRICE)));
        return orderItem;
    }

    @Override
    public ArrayList<OrderItem> getAll() {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        Cursor c = query(TABLE_NAME, new String[]{COL_ORDER_ID, COL_PRODUCT_ID, COL_QUANTITY, COL_LOCAL_PRICE}, null, null, null);

        if (c != null) {
            while (c.moveToNext()) {
                orderItems.add(cursorToEntity(c));
            }
            c.close();
        }
        return orderItems;
    }

    public ArrayList<OrderItem> getItemsByOrderId(int orderId) {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        Cursor c = query(TABLE_NAME, new String[]{COL_ORDER_ID, COL_PRODUCT_ID, COL_QUANTITY, COL_LOCAL_PRICE},
                COL_ORDER_ID + " = ?", new String[]{String.valueOf(orderId)}, null);

        if (c != null) {
            while (c.moveToNext()) {
                orderItems.add(cursorToEntity(c));
            }
            c.close();
        }
        return orderItems;
    }
}
