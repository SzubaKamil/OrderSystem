package com.company.ordersystem.dao.company.impl;

import com.company.ordersystem.dao.Crud;
import com.company.ordersystem.dao.company.ICompanyDAO;
import com.company.ordersystem.entity.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CompanyDAOImplDAO extends Crud<Company> implements ICompanyDAO {

    @Autowired
    public CompanyDAOImplDAO(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Company get(int id) {
        return getById(id, Company.class);
    }

    @Override
    public List<Company> getAll() {
        return getAll("Company", Company.class);
    }

    @Transactional
    @Override
    public boolean saveOrUpdate(Company Company) {
        return createOrUpdate(Company);
    }

    @Transactional
    @Override
    public boolean delete(Company Company) {
        return remove(Company);
    }
}
