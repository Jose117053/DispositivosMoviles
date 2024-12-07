package com.example.postresycafe.DataBase.Services;

import android.content.Context;
import android.database.Cursor;

import com.example.postresycafe.DataBase.CRUD.UserDB;
import com.example.postresycafe.DataBase.Entities.User;


import java.util.ArrayList;

public class UserManager implements EntityManager<User> {

    private final UserDB userDB;

    public UserManager(Context context) {
        this.userDB = new UserDB(context);
    }

    @Override
    public long insert(User user) {
        userDB.openForWrite();
        long result = userDB.insertUser(user);
        userDB.close();
        return result;
    }

    @Override
    public ArrayList<User> getAll() {
        userDB.openForRead();
        ArrayList<User> users = userDB.getAll();
        userDB.close();
        return users;
    }

    @Override
    public User getById(int id) {
        return userDB.getById(id);
    }

    public User getByUsername(String username) {
        return userDB.getUserByUsername(username);
    }

    @Override
    public boolean delete(int id) {
        userDB.openForWrite();
        boolean result = userDB.delete("Users", "idUser = ?", new String[]{String.valueOf(id)}) > 0;
        userDB.close();
        return result;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    public int initializeDefaultUserr() {
        ArrayList<User> defaultUsers = new ArrayList<>();
        defaultUsers.add(new User("Prueba", "correoPrueba@gmail.com", "123"));

        userDB.openForWrite();
        int addedCount = 0;
        for (User user : defaultUsers) {
            userDB.insertUser(user);
            addedCount++;
        }
        userDB.close();
        return addedCount;
    }


}
