package com.company.ordersystem.dao.order.impl;

import com.company.ordersystem.dao.Crud;
import com.company.ordersystem.dao.order.IPaymentTermDAO;
import com.company.ordersystem.entity.order.PaymentTerm;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PaymentTermDAOImpl extends Crud<PaymentTerm> implements IPaymentTermDAO {

    public PaymentTermDAOImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public PaymentTerm get(int id) {
        return getById(id, PaymentTerm.class);
    }

    @Override
    public List<PaymentTerm> getAll() {
        return getAll("PaymentTerm", PaymentTerm.class);
    }

    @Transactional
    @Override
    public boolean saveOrUpdate(PaymentTerm paymentTerm) {
        return createOrUpdate(paymentTerm);
    }

    @Transactional
    @Override
    public boolean delete(PaymentTerm paymentTerm) {
        return remove(paymentTerm);
    }
}
