package com.company.ordersystem.service.company;

import com.company.ordersystem.entity.company.Contact;
import com.company.ordersystem.service.ICrudService;

import java.util.List;

public interface IContactService extends ICrudService<Contact> {
    List<Contact> getByAddressId(int addressId);

}
