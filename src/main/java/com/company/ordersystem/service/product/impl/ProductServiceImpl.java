package com.company.ordersystem.service.product.impl;

import com.company.ordersystem.dao.product.ICodeDAO;
import com.company.ordersystem.dao.product.IProductDAO;
import com.company.ordersystem.entity.product.Code;
import com.company.ordersystem.entity.product.Product;
import com.company.ordersystem.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    IProductDAO productDAO;

    @Override
    public Product get(int id) {
        return productDAO.get(id);
    }

    @Override
    public List<Product> getAll() {
        return  productDAO.getAll();
    }

    @Override
    public boolean saveOrUpdate(Product product) {
        return productDAO.saveOrUpdate(product);
    }

    @Override
    public boolean delete(Product product) {
        return productDAO.delete(product);
    }

    @Override
    public List<Product> search(Product product) {
        return productDAO.search(product);
    }
}
