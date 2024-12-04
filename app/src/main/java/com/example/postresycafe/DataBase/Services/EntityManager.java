package com.example.postresycafe.DataBase.Services;

import java.util.ArrayList;

public interface EntityManager<T> {
    long add(T entity);
    ArrayList<T> getAll();
    T getById(int id);
    boolean delete(int id);
    boolean update(T entity);
}
