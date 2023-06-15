package com.company.ordersystem.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

public abstract class Crud <T>{

    protected EntityManager entityManager;

    @Autowired
    public Crud(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<T> getAll(String tableName, Class<T> tClass ) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<T> theQuery = currentSession.createQuery("from " + tableName + " ORDER BY id DESC " , tClass);
//        Query<T> theQuery = currentSession.createQuery("from " + tableName , tClass);
        return theQuery.getResultList();
    }

    public T getById(int id, Class<T> tClass ) {
        return entityManager.find(tClass, id);
    }

    @Transactional
    public boolean createOrUpdate(T  object) {
        Session currentSession = entityManager.unwrap(Session.class);
        try {
            currentSession.saveOrUpdate(object);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Transactional
    public boolean remove(T object) {
        Session currentSession = entityManager.unwrap(Session.class);
        try {
            currentSession.delete(object);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
