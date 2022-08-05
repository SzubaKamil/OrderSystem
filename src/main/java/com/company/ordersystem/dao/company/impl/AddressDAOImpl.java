package com.company.ordersystem.dao.company.impl;

import com.company.ordersystem.dao.Crud;
import com.company.ordersystem.dao.company.IAddressDAO;
import com.company.ordersystem.entity.company.Address;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import java.util.List;

@Repository
public class AddressDAOImpl extends Crud<Address> implements IAddressDAO {

    public AddressDAOImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Address get(int id) {
        return getById(id, Address.class);
    }

    @Override
    public List<Address> getAll() {
        return getAll("Address", Address.class);
    }

    @Transactional
    @Override
    public boolean saveOrUpdate(Address address) {
        return createOrUpdate(address);
    }

    @Transactional
    @Override
    public boolean delete(Address address) {
        return remove(address);
    }

    @Override
    public List<Address> getDeliveryAddresses() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Address> theQuery = currentSession.createQuery("from Address where isDelivery=:delivery_address_id", Address.class);
        theQuery.setParameter("delivery_address_id", true);
        try {
            return theQuery.getResultList();
        }
        catch (NonUniqueResultException e){
            System.out.println("NO RESULT Exc");
            return null;
        }
    }
}
