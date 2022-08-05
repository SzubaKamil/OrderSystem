package com.company.ordersystem.service.order.impl;

import com.company.ordersystem.dao.order.IOrderDAO;
import com.company.ordersystem.entity.order.Order;
import com.company.ordersystem.service.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    IOrderDAO orderDAO;

    @Override
    public Order get(int id) {
        return orderDAO.get(id);
    }

    @Override
    public List<Order> getAll() {
        return orderDAO.getAll();
    }

    @Override
    public boolean saveOrUpdate(Order order) {
        return orderDAO.saveOrUpdate(order);
    }

    @Override
    public boolean delete(Order order) {
        return orderDAO.delete(order);
    }

    @Override
    public List<Order> search(Order order) {
        return orderDAO.search(order);
    }

    @Override
    public List<Order> getByProductId(int productId) {
        return orderDAO.getByProductId(productId);
    }

    @Override
    public List<Order> getByContractorID(int contractorId) {
        return orderDAO.getByContractorID(contractorId);
    }
}
