package com.company.ordersystem.service.order;

import com.company.ordersystem.entity.order.Campaign;
import com.company.ordersystem.service.ICrudService;
import com.company.ordersystem.service.ISearchService;

public interface ICampaignService extends ICrudService<Campaign>, ISearchService<Campaign> {
}
