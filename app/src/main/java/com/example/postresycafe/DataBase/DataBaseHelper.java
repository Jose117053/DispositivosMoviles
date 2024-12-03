package com.example.postresycafe.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String TABLE_USERS = "Users";
    private static final String TABLE_PRODUCTS= "PRODUCTS";
    private static final String TABLE_ORDERS = "ORDERS";

    private static final String DATABASE_NAME = "CafePostres.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USERS + "(" +
            "idUser INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "username TEXT UNIQUE NOT NULL, " +
            "email TEXT UNIQUE NOT NULL, " +
            "password TEXT NOT NULL)";

    private static final String CREATE_TABLE_PRODUCTS = "CREATE TABLE " + TABLE_PRODUCTS + "(" +
            "idProduct INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nameProduct TEXT NOT NULL, " +
            "description TEXT, " +
            "price REAL NOT NULL," +
            "CONSTRAINT unique_user_email UNIQUE (username, email))";

    private static final String CREATE_TABLE_ORDERS = "CREATE TABLE " +TABLE_ORDERS + "(" +
            "idOrder INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "idUser INTEGER NOT NULL, " +
            "total_price REAL NOT NULL)";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Crear tablas al crear la base de datos
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_PRODUCTS);
        db.execSQL(CREATE_TABLE_ORDERS);
        //db.execSQL("INSERT INTO products (name, description, price) VALUES " +
          //      "('Café Americano', 'Café clásico', 30.0), " +
            //    "('Brownie', 'Postre de chocolate', 50.0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS products");
        onCreate(db);
    }
}
