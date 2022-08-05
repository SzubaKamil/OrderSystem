package com.company.ordersystem.service.product;


import com.company.ordersystem.entity.product.Code;
import com.company.ordersystem.entity.product.Product;
import com.company.ordersystem.service.ICrudService;
import com.company.ordersystem.service.ISearchService;

import java.util.List;

public interface IProductService extends ICrudService<Product>, ISearchService<Product> {

}
