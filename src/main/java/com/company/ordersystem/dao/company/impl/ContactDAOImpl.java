package com.company.ordersystem.dao.company.impl;

import com.company.ordersystem.dao.Crud;
import com.company.ordersystem.dao.company.IContactDAO;
import com.company.ordersystem.entity.company.Contact;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import java.util.List;

@Repository
public class ContactDAOImpl extends Crud<Contact> implements IContactDAO {

    public ContactDAOImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Contact get(int id) {
        return getById(id, Contact.class);
    }

    @Override
    public List<Contact> getAll() {
        return getAll("Contact", Contact.class);
    }

    @Transactional
    @Override
    public boolean saveOrUpdate(Contact contact) {
        return createOrUpdate(contact);
    }

    @Transactional
    @Override
    public boolean delete(Contact contact) {
        return remove(contact);
    }

    @Override
    public List<Contact> getByAddressId(int addressId) {
        // get by parameter

        Session currentSession = entityManager.unwrap(Session.class);
        Query<Contact> theQuery = currentSession.createQuery("from Contact where addressId=:address_id", Contact.class);
        theQuery.setParameter("address_id", addressId);
        try {
            return theQuery.getResultList();
        }
        catch (NonUniqueResultException e){
            System.out.println("NO RESULT Exc");
            return null;
        }
    }

}
