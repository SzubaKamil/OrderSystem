package com.company.ordersystem.service.order;

import com.company.ordersystem.entity.order.Order;
import com.company.ordersystem.service.ICrudService;
import com.company.ordersystem.service.ISearchService;

import java.util.List;

public interface IOrderService extends ICrudService<Order>, ISearchService<Order> {
    List<Order> getByProductId(int productId);
    List<Order> getByContractorID(int contractorId);
}
