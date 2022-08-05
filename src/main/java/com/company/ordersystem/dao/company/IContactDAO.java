package com.company.ordersystem.dao.company;

import com.company.ordersystem.dao.ICrudDAO;
import com.company.ordersystem.entity.company.Contact;

import java.util.List;

public interface IContactDAO extends ICrudDAO<Contact> {

    List<Contact> getByAddressId (int addressId);
}
