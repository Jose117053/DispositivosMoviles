package com.example.postresycafe.DataBase.CRUD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.postresycafe.DataBase.Entities.User;

import java.util.ArrayList;

public class UserDB extends GeneralOperationsDB<User> {

    private static final String TABLE_NAME = "Users";
    private static final String COL_ID = "idUser";
    private static final String COL_USERNAME = "username";
    private static final String COL_EMAIL = "email";
    private static final String COL_PASSWORD = "password";

    public UserDB(Context context) {
        super(context);
    }

    public long insertUser(User user) {
        ContentValues values = new ContentValues();
        values.put(COL_USERNAME, user.getUsername());
        values.put(COL_EMAIL, user.getEmail());
        values.put(COL_PASSWORD, user.getPassword());
        return insert(TABLE_NAME, values);
    }

    public User getUserByUsername(String username) {
        openForRead();
        Cursor c = query(TABLE_NAME, new String[]{COL_ID, COL_USERNAME, COL_EMAIL, COL_PASSWORD},
                COL_USERNAME + " = ?", new String[]{username}, null);

        if (c != null && c.moveToFirst()) {
            User user = cursorToEntity(c);
            c.close();
            return user;
        }
        return null;
    }
    public User getById(int id) {
        openForRead();
        Cursor c = query(TABLE_NAME, new String[]{COL_ID, COL_USERNAME, COL_EMAIL, COL_PASSWORD},
                COL_ID + " = ?", new String[]{Integer.toString(id)}, null);

        if (c != null && c.moveToFirst()) {
            User user = cursorToEntity(c);
            c.close();
            return user;
        }
        return null;
    }

    @Override
    public User cursorToEntity(Cursor c) {
        User user = new User();
        user.setIdUser(c.getInt(c.getColumnIndexOrThrow(COL_ID)));
        user.setUsername(c.getString(c.getColumnIndexOrThrow(COL_USERNAME)));
        user.setEmail(c.getString(c.getColumnIndexOrThrow(COL_EMAIL)));
        user.setPassword(c.getString(c.getColumnIndexOrThrow(COL_PASSWORD)));
        return user;
    }

    @Override
    public ArrayList<User> getAll() {
        ArrayList<User> users = new ArrayList<>();
        Cursor c = query(TABLE_NAME, new String[]{COL_ID, COL_USERNAME, COL_EMAIL, COL_PASSWORD}, null, null, COL_USERNAME);

        if (c != null) {
            while (c.moveToNext()) {
                users.add(cursorToEntity(c));
            }
            c.close();
        }
        return users;
    }

    public boolean getUser(String username) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE username = ?", new String[]{username});

        // Si encontramos registros, significa que el nombre de usuario ya existe
        boolean exists = cursor.getCount() > 0;
        cursor.close();

        return exists;
    }

}
