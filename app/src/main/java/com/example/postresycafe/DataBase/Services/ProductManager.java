package com.example.postresycafe.DataBase.Services;

import android.content.Context;

import com.example.postresycafe.DataBase.CRUD.ProductDB;
import com.example.postresycafe.DataBase.Entities.Product;

import java.util.ArrayList;

public class ProductManager implements EntityManager<Product> {

    private final ProductDB productDB;

    public ProductManager(Context context) {
        this.productDB = new ProductDB(context);
    }

    @Override
    public long insert(Product product) {
        productDB.openForWrite();
        long result = productDB.insertProduct(product);
        productDB.close();
        return result;
    }

    @Override
    public ArrayList<Product> getAll() {
        productDB.openForRead();
        ArrayList<Product> products = productDB.getAll();
        productDB.close();
        return products;
    }

    @Override
    public Product getById(int id) {
        productDB.openForRead();
        ArrayList<Product> products = productDB.getAll();
        productDB.close();
        for (Product product : products) {
            if (product.getIdProduct() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        productDB.openForWrite();
        boolean result = productDB.delete("PRODUCTS", "idProduct = ?", new String[]{String.valueOf(id)}) > 0;
        productDB.close();
        return result;
    }

    @Override
    public boolean update(Product product) {

        return false;
    }

    public int initializeDefaultProducts() {
        ArrayList<Product> defaultProducts = new ArrayList<>();
        defaultProducts.add(new Product("Batido", "Batido de frutas", 80.0));
        defaultProducts.add(new Product("Pastel", "Pastel de chocolate", 120.0));
        defaultProducts.add(new Product("Café", "Café Americano", 50.0));
        defaultProducts.add(new Product("Pan", "Pan dulce", 30.0));

        productDB.openForWrite();
        int addedCount = 0;
        for (Product product : defaultProducts) {
            productDB.insertProduct(product);
            addedCount++;
        }
        productDB.close();
        return addedCount;
    }



}


