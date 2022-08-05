package com.company.ordersystem.dao.order;

import com.company.ordersystem.dao.ICrudDAO;
import com.company.ordersystem.dao.ISearchDAO;
import com.company.ordersystem.entity.order.Order;

import java.util.List;

public interface IOrderDAO extends ICrudDAO<Order>, ISearchDAO<Order> {

    List<Order> getByProductId(int productId);

    List<Order> getByContractorID(int contractorId);
}
