package com.company.ordersystem.dao.company;

import com.company.ordersystem.dao.ICrudDAO;
import com.company.ordersystem.entity.company.Address;

import java.util.List;

public interface IAddressDAO extends ICrudDAO<Address> {
    List<Address> getDeliveryAddresses();
}
