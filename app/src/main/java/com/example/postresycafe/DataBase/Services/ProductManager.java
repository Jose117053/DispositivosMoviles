package com.example.postresycafe.DataBase.Services;

import android.content.Context;
import android.database.Cursor;

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
        return productDB.getById(id);
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
        defaultProducts.add(new Product("Cafe", "cafe negro con 2 cucharadas de azucar", 50.0));
        defaultProducts.add(new Product("Pastel", "Pastel de chocolate, con extra chantilly", 100.0));
        defaultProducts.add(new Product("Chocolate", "Chocolate amargo con relleno de lechera", 75.0));
        defaultProducts.add(new Product("Pan", "Pan dulce", 30.0));
        defaultProducts.add(new Product("Yogurt con Frutas", "Incluye fruta de mango, fresa, arandanos y manzana", 60.0));
        defaultProducts.add(new Product("Batido", "de leche con chocolate", 70.0));

        productDB.openForWrite();
        int addedCount = 0;
        for (Product product : defaultProducts) {
            productDB.insertProduct(product);
            addedCount++;
        }
        productDB.close();
        return addedCount;
    }


    public ArrayList<Product> getProductsByOrderId(int orderId) {
        return productDB.getProductsByOrderId(orderId);
    }


}


