package com.company.ordersystem.dao.order.impl;

import com.company.ordersystem.dao.Crud;
import com.company.ordersystem.dao.order.IOrderDAO;
import com.company.ordersystem.dao.product.IProductDAO;
import com.company.ordersystem.entity.order.Order;
import com.company.ordersystem.entity.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDAOImpl extends Crud<Order> implements IOrderDAO {

    @Autowired
    IProductDAO productDAO;

    @Autowired
    public OrderDAOImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Order get(int id) {
        return getById(id, Order.class);
    }

    @Override
    public List<Order> getAll() {
        return getAll("Order", Order.class);
    }

    @Transactional
    @Override
    public boolean saveOrUpdate(Order order) {
        return createOrUpdate(order);
    }

    @Transactional
    @Override
    public boolean delete(Order order) {
        return remove(order);
    }

    @Override
    public List<Order> search(Order order) {
        List<Product> products = productDAO.search(order.getProduct());
        List<Order> orders = new ArrayList<>();

        for (Product product: products){
            try {
                orders.addAll(searchByParameter(entityManager, Order.class, "Order", "product_id", String.valueOf(product.getId())));
            }
            catch (NoResultException e){
                System.out.println("No order for product");
            }
        }
        return orders;
    }

    @Override
    public List<Order> getByProductId(int productId) {
        return searchByParameter(entityManager, Order.class, "Order", "product_id", String.valueOf(productId));
    }

    @Override
    public List<Order> getByContractorID(int contractorId) {
        return searchByParameter(entityManager, Order.class, "Order", "contractor_id", String.valueOf(contractorId));
    }
}
