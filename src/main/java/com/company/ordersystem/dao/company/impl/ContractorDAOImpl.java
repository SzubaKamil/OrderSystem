package com.company.ordersystem.dao.company.impl;

import com.company.ordersystem.dao.Crud;
import com.company.ordersystem.dao.company.IContractorDAO;
import com.company.ordersystem.entity.company.Contractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ContractorDAOImpl extends Crud<Contractor> implements IContractorDAO {

    @Autowired
    public ContractorDAOImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Contractor get(int id) {
        return getById(id, Contractor.class );
    }

    @Override
    public List<Contractor> getAll() {
        return getAll("Contractor", Contractor.class);
    }

    @Transactional
    @Override
    public boolean saveOrUpdate(Contractor contractor) {
        return createOrUpdate(contractor);
    }

    @Transactional
    @Override
    public boolean delete(Contractor contractor) {
        return remove(contractor);
    }
}
