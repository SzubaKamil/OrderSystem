package com.company.ordersystem.dao.product.impl;

import com.company.ordersystem.dao.Crud;
import com.company.ordersystem.dao.product.IFormatDAO;
import com.company.ordersystem.dao.product.IProductDetails;
import com.company.ordersystem.entity.product.Format;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class FormatDAOImpl extends Crud<Format> implements IFormatDAO, IProductDetails<Format> {

    @Autowired
    public FormatDAOImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Format get(int id) {
        return getById(id, Format.class);
    }

    @Override
    public List<Format> getAll() {
        return getAll("Format", Format.class);
    }

    @Override
    public boolean saveOrUpdate(Format format) {
        if (isExist(format.getFormat() , entityManager, Format.class, "Format")){
            return false;
        }
        return createOrUpdate(format);
    }

    @Override
    @Transactional
    public boolean delete(Format format) {
        return remove(format);
    }
}
