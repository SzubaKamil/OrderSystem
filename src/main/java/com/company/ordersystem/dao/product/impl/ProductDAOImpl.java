package com.company.ordersystem.dao.product.impl;

import com.company.ordersystem.dao.Crud;
import com.company.ordersystem.dao.product.IProductDAO;
import com.company.ordersystem.entity.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Comparator;
import java.util.List;

@Repository
public class ProductDAOImpl extends Crud<Product> implements IProductDAO {


    @Autowired
    public ProductDAOImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Product get(int id) {
        return getById(id, Product.class);
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = getAll("Product", Product.class);
        products.sort(Comparator.comparing(Product::getName));

        return  products;
    }

    @Override
    public List<Product> search(Product t) {
        return searchByParameter(entityManager, Product.class, "Product", "name", t.getName() );
    }

    @Transactional
    @Override
    public boolean saveOrUpdate(Product product) {
        return createOrUpdate(product);
    }

    @Transactional
    @Override
    public boolean delete(Product product) {
        return remove(product);
    }

}
