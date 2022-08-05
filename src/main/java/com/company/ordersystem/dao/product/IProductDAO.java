package com.company.ordersystem.dao.product;

import com.company.ordersystem.dao.ICrudDAO;
import com.company.ordersystem.dao.ISearchDAO;
import com.company.ordersystem.entity.product.Product;


public interface IProductDAO extends ICrudDAO<Product>, ISearchDAO<Product> {

}
