package com.company.ordersystem.dao.product.impl;

import com.company.ordersystem.dao.Crud;
import com.company.ordersystem.dao.product.IColorDAO;
import com.company.ordersystem.dao.product.IProductDetails;
import com.company.ordersystem.entity.product.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ColorDAOImpl extends Crud<Color> implements IColorDAO, IProductDetails<Color> {

    @Autowired
    public ColorDAOImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Color get(int id) {
        return getById(id, Color.class);
    }

    @Override
    public List<Color> getAll() {
        return getAll("Color", Color.class);
    }

    @Transactional
    @Override
    public boolean saveOrUpdate(Color color) {
        if (isExist(color.getValue() , entityManager, Color.class, "Color")){
            return false;
        }
        return createOrUpdate(color);
    }

    @Transactional
    @Override
    public boolean delete(Color color) {
        return remove(color);
    }

}
