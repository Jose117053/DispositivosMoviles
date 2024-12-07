package com.example.postresycafe.DataBase.CRUD;

import com.example.postresycafe.DataBase.Entities.OrderItem;
import com.example.postresycafe.DataBase.Entities.Product;
import com.example.postresycafe.DataBase.Entities.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;


public class ProductDB extends GeneralOperationsDB<Product> {

    private static final String TABLE_NAME = "PRODUCTS";
    private static final String COL_ID = "idProduct";
    private static final String COL_NAME = "nameProduct";
    private static final String COL_DESC = "description";
    private static final String COL_PRICE = "price";

    public ProductDB(Context context) {
        super(context);
    }

    public long insertProduct(Product product) {
        ContentValues values = new ContentValues();
        values.put(COL_NAME, product.getNameProduct());
        values.put(COL_DESC, product.getDescription());
        values.put(COL_PRICE, product.getPrice());
        return insert(TABLE_NAME, values);
    }

    @Override
    protected Product cursorToEntity(Cursor c) {
        Product product = new Product();
        product.setIdProduct(c.getInt(c.getColumnIndexOrThrow(COL_ID)));
        product.setNameProduct(c.getString(c.getColumnIndexOrThrow(COL_NAME)));
        product.setDescription(c.getString(c.getColumnIndexOrThrow(COL_DESC)));
        product.setPrice(c.getDouble(c.getColumnIndexOrThrow(COL_PRICE)));
        return product;
    }

    @Override
    public ArrayList<Product> getAll() {
        ArrayList<Product> products = new ArrayList<>();
        Cursor c = query(TABLE_NAME, new String[]{COL_ID, COL_NAME, COL_DESC, COL_PRICE}, null, null, COL_NAME);

        if (c != null) {
            while (c.moveToNext()) {
                products.add(cursorToEntity(c));
            }
            c.close();
        }
        return products;
    }

    public Product getById(int id) {
        openForRead();
        Cursor c = query(TABLE_NAME, new String[]{COL_ID, COL_NAME, COL_DESC, COL_PRICE},
                COL_ID + " = ?", new String[]{Integer.toString(id)}, null);

        if (c != null && c.moveToFirst()) {
            Product product = cursorToEntity(c);
            c.close();
            return product;
        }
        return null;
    }

    public ArrayList<Product> getProductsByOrderId(int orderId) {
        ArrayList<Product> products = new ArrayList<>();
        Cursor c = query(TABLE_NAME, new String[]{COL_ID, COL_NAME, COL_DESC, COL_PRICE},
                COL_ID + " = ?", new String[]{String.valueOf(orderId)}, null);

        if (c != null) {
            while (c.moveToNext()) {
                products.add(cursorToEntity(c));
            }
            c.close();
        }
        return products;
    }
}