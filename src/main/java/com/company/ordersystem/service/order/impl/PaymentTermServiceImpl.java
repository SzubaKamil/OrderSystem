package com.company.ordersystem.service.order.impl;

import com.company.ordersystem.dao.order.IPaymentTermDAO;
import com.company.ordersystem.entity.order.PaymentTerm;
import com.company.ordersystem.service.order.IPaymentTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentTermServiceImpl implements IPaymentTermService {

    @Autowired
    IPaymentTermDAO paymentTermDAO;

    @Override
    public PaymentTerm get(int id) {
        return paymentTermDAO.get(id);
    }

    @Override
    public List<PaymentTerm> getAll() {
        return paymentTermDAO.getAll();
    }

    @Override
    public boolean saveOrUpdate(PaymentTerm paymentTerm) {
        return paymentTermDAO.saveOrUpdate(paymentTerm);
    }

    @Override
    public boolean delete(PaymentTerm paymentTerm) {
        return paymentTermDAO.delete(paymentTerm);
    }
}
