package com.company.ordersystem.dao.product.impl;

import com.company.ordersystem.dao.Crud;
import com.company.ordersystem.dao.product.ICodeDAO;
import com.company.ordersystem.entity.product.Code;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CodeDAOImpl extends Crud<Code> implements ICodeDAO {

    @Autowired
    public CodeDAOImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Code get(int id) {
        return getById(id, Code.class);
    }

    @Override
    public List<Code> getAll() {
        return null;
    }

    @Transactional
    @Override
    public boolean saveOrUpdate(Code code) {
        return createOrUpdate(code);
    }

    @Transactional
    @Override
    public boolean delete(Code code) {
        try {
            entityManager.remove(entityManager.contains(code) ? code : entityManager.merge(code));
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
