package com.company.ordersystem.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import java.util.List;

public interface ISearchDAO <T>{
    List<T> search(T t);
    default List<T> searchByParameter(EntityManager entityManager, Class<T> tClass,
                                      String tableName, String searchParameter, String searchValue){

        Session currentSession = entityManager.unwrap(Session.class);
        Query<T> theQuery = currentSession.createQuery("from " + tableName + " where lower(" + searchParameter + ") " +
                "like :" + searchParameter, tClass );

        theQuery.setParameter(searchParameter, "%" + searchValue.toLowerCase() + "%");

        return theQuery.getResultList();
    }

}
