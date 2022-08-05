package com.company.ordersystem.dao.user;

import com.company.ordersystem.dao.ICrudDAO;
import com.company.ordersystem.entity.user.User;

public interface IUserDAO extends ICrudDAO<User> {

    User get(String username);
}
