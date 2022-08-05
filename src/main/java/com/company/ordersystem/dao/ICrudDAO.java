package com.company.ordersystem.dao;

import java.util.List;

public interface ICrudDAO<T> {

    T get(int id);
    List<T> getAll();
    boolean saveOrUpdate(T t);
    boolean delete(T t);
}
