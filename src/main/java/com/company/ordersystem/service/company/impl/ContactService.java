package com.company.ordersystem.service.company.impl;

import com.company.ordersystem.dao.company.IContactDAO;
import com.company.ordersystem.entity.company.Contact;
import com.company.ordersystem.service.company.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService implements IContactService {

    @Autowired
    IContactDAO contactDAO;

    @Override
    public Contact get(int id) {
        return contactDAO.get(id);
    }

    @Override
    public List<Contact> getAll() {
        return contactDAO.getAll();
    }

    @Override
    public boolean saveOrUpdate(Contact contact) {
        return contactDAO.saveOrUpdate(contact);
    }

    @Override
    public boolean delete(Contact contact) {
        return contactDAO.delete(contact);
    }

    @Override
    public List<Contact> getByAddressId(int addressId) {
        return contactDAO.getByAddressId(addressId);
    }
}
