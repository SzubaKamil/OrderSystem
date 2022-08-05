package com.company.ordersystem.dao.product;

import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

public interface IProductDetails<T> {

    default  boolean isExist (String value, EntityManager entityManager, Class<T> tClass, String tableName ){
        Session currentSession = entityManager.unwrap(Session.class);
        Query<T> theQuery = currentSession.createQuery("from " + tableName + " where value=:value", tClass);
        theQuery.setParameter("value", value);
        try {
            theQuery.getSingleResult();
        }
        catch (NonUniqueResultException | NoResultException e){
            System.out.println("NO RESULT Exc for product details");
            return false;
        }
        return true;
    }
}
