package com.company.ordersystem.dao.order.impl;

import com.company.ordersystem.dao.Crud;
import com.company.ordersystem.dao.order.ICampaignDAO;
import com.company.ordersystem.entity.order.Campaign;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CampaignDAOImpl extends Crud<Campaign> implements ICampaignDAO {

    public CampaignDAOImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Campaign get(int id) {
        return getById(id, Campaign.class);
    }

    @Override
    public List<Campaign> getAll() {
        return getAll("Campaign", Campaign.class);
    }

    @Transactional
    @Override
    public boolean saveOrUpdate(Campaign campaign) {
        return createOrUpdate(campaign);
    }

    @Transactional
    @Override
    public boolean delete(Campaign campaign) {
        return remove(campaign);
    }

    @Override
    public List<Campaign> search(Campaign campaign) {
        return searchByParameter(entityManager, Campaign.class, "Campaign", "name", campaign.getName());
    }
}
