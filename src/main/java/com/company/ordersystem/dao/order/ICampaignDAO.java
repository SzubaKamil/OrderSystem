package com.company.ordersystem.dao.order;

import com.company.ordersystem.dao.ICrudDAO;
import com.company.ordersystem.dao.ISearchDAO;
import com.company.ordersystem.entity.order.Campaign;

public interface ICampaignDAO extends ICrudDAO<Campaign>, ISearchDAO<Campaign> {
}
