package com.company.ordersystem.service;

import java.util.List;

public interface ISearchService <T>{
    List<T> search(T t);
}
