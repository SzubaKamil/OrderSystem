package com.company.ordersystem.service.company.impl;

import com.company.ordersystem.dao.company.IAddressDAO;
import com.company.ordersystem.entity.company.Address;
import com.company.ordersystem.service.company.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    IAddressDAO addressDAO;

    @Override
    public Address get(int id) {
        return addressDAO.get(id);
    }

    @Override
    public List<Address> getAll() {
        return addressDAO.getAll();
    }

    @Override
    public boolean saveOrUpdate(Address address) {
        return addressDAO.saveOrUpdate(address);
    }

    @Override
    public boolean delete(Address address) {
        return addressDAO.delete(address);
    }

    @Override
    public List<Address> getDeliveryAddresses() {
        return addressDAO.getDeliveryAddresses();
    }
}
