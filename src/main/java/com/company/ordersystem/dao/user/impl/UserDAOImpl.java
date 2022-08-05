package com.company.ordersystem.dao.user.impl;

import com.company.ordersystem.dao.Crud;
import com.company.ordersystem.dao.user.IUserDAO;
import com.company.ordersystem.entity.user.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import java.util.List;

@Repository
public class UserDAOImpl extends Crud<User> implements IUserDAO {

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return getAll("User", User.class);
    }

    @Transactional
    @Override
    public boolean saveOrUpdate(User user) {
        return createOrUpdate(user);
    }

    @Transactional
    @Override
    public boolean delete(User user) {
        return remove(user);
    }

    @Override
    public User get(String username) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> theQuery = currentSession.createQuery("from User where username=:username", User.class);
        theQuery.setParameter("username", username);
        try {
            return theQuery.getSingleResult();
        }
        catch (NonUniqueResultException e){
            System.out.println("NO RESULT Exc");
            return null;
        }
    }
}
