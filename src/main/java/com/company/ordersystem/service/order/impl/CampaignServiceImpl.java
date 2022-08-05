package com.company.ordersystem.service.order.impl;

import com.company.ordersystem.dao.order.ICampaignDAO;
import com.company.ordersystem.entity.order.Campaign;
import com.company.ordersystem.service.order.ICampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignServiceImpl implements ICampaignService {

    @Autowired
    ICampaignDAO campaignDAO;

    @Override
    public Campaign get(int id) {
        return campaignDAO.get(id);
    }

    @Override
    public List<Campaign> getAll() {
        return campaignDAO.getAll();
    }

    @Override
    public boolean saveOrUpdate(Campaign campaign) {
        return campaignDAO.saveOrUpdate(campaign);
    }

    @Override
    public boolean delete(Campaign campaign) {
        return campaignDAO.delete(campaign);
    }

    @Override
    public List<Campaign> search(Campaign campaign) {
        return campaignDAO.search(campaign);
    }
}
