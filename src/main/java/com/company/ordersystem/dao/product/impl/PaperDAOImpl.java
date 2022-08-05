package com.company.ordersystem.dao.product.impl;

import com.company.ordersystem.dao.Crud;
import com.company.ordersystem.dao.product.IPaperDAO;
import com.company.ordersystem.dao.product.IProductDetails;
import com.company.ordersystem.entity.product.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PaperDAOImpl extends Crud<Paper> implements IPaperDAO, IProductDetails<Paper> {

    @Autowired
    public PaperDAOImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Paper get(int id) {
        return getById(id, Paper.class);
    }

    @Override
    public List<Paper> getAll() {
        return getAll("Paper", Paper.class);
    }

    @Override
    public boolean saveOrUpdate(Paper paper) {
        if (isExist(paper.getValue() , entityManager, Paper.class, "Paper")){
            return false;
        }
        return createOrUpdate(paper);
    }

    @Override
    @Transactional
    public boolean delete(Paper paper) {
        return remove(paper);
    }
}
