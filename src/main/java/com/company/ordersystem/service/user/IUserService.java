package com.company.ordersystem.service.user;

import com.company.ordersystem.entity.user.User;
import com.company.ordersystem.service.ICrudService;

public interface IUserService extends ICrudService<User> {
    User get(String username);
}
