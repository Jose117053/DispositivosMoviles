package com.example.postresycafe.DataBase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public abstract class OperacionesGeneralesBD<T> {

    protected SQLiteDatabase bdd;
    protected DataBaseHelper dbHelper;

    public OperacionesGeneralesBD(Context context) {
        dbHelper = new DataBaseHelper(context);
    }

    public void openForWrite() {
        bdd = dbHelper.getWritableDatabase();
    }

    public void openForRead() {
        bdd = dbHelper.getReadableDatabase();
    }

    public void close() {
        bdd.close();
    }

    // Métodos genéricos para CRUD
    public long insert(String tableName, ContentValues values) {
        return bdd.insert(tableName, null, values);
    }

    public int update(String tableName, ContentValues values, String whereClause, String[] whereArgs) {
        return bdd.update(tableName, values, whereClause, whereArgs);
    }

    public int delete(String tableName, String whereClause, String[] whereArgs) {
        return bdd.delete(tableName, whereClause, whereArgs);
    }

    public Cursor query(String tableName, String[] columns, String selection, String[] selectionArgs, String orderBy) {
        return bdd.query(tableName, columns, selection, selectionArgs, null, null, orderBy);
    }

    // Métodos abstractos para personalizar el manejo de entidades
    protected abstract T cursorToEntity(Cursor cursor);

    public abstract ArrayList<T> getAll();
}