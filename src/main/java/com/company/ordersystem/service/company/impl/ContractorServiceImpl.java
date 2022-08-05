package com.company.ordersystem.service.company.impl;

import com.company.ordersystem.dao.company.IContractorDAO;
import com.company.ordersystem.entity.company.Contractor;
import com.company.ordersystem.service.company.IContractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractorServiceImpl implements IContractorService {

    @Autowired
    IContractorDAO contractorDAO;

    @Override
    public Contractor get(int id) {
        return contractorDAO.get(id);
    }

    @Override
    public List<Contractor> getAll() {
        return contractorDAO.getAll();
    }

    @Override
    public boolean saveOrUpdate(Contractor contractor) {
        return contractorDAO.saveOrUpdate(contractor);
    }

    @Override
    public boolean delete(Contractor contractor) {
        return contractorDAO.delete(contractor);
    }
}
