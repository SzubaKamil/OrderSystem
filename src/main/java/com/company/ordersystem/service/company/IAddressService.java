package com.company.ordersystem.service.company;

import com.company.ordersystem.entity.company.Address;
import com.company.ordersystem.service.ICrudService;

import java.util.List;

public interface IAddressService extends ICrudService<Address> {

    List<Address> getDeliveryAddresses();
}
