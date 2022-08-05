package com.company.ordersystem.service;

import java.util.List;

public interface ICrudService<T> {

    T get(int id);
    List<T> getAll();
    boolean saveOrUpdate(T t);
    boolean delete(T t);
}
