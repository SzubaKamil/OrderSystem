package com.company.ordersystem.service.company.impl;

import com.company.ordersystem.dao.company.ICompanyDAO;
import com.company.ordersystem.entity.company.Company;
import com.company.ordersystem.service.company.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements ICompanyService {

    @Autowired
    ICompanyDAO companyDAO;

    @Override
    public Company get(int id) {
        return companyDAO.get(id);
    }

    @Override
    public List<Company> getAll() {
        return companyDAO.getAll();
    }

    @Override
    public boolean saveOrUpdate(Company company) {
        return companyDAO.saveOrUpdate(company);
    }

    @Override
    public boolean delete(Company company) {
        return companyDAO.delete(company);
    }
}
