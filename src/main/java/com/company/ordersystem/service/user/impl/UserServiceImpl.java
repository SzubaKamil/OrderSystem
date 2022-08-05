package com.company.ordersystem.service.user.impl;

import com.company.ordersystem.dao.user.IUserDAO;
import com.company.ordersystem.entity.user.User;
import com.company.ordersystem.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserDAO userDAO;

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public boolean saveOrUpdate(User user) {
        return userDAO.saveOrUpdate(user);
    }

    @Override
    public boolean delete(User user) {
        return userDAO.delete(user);
    }

    @Override
    public User get(String username) {
        return userDAO.get(username);
    }
}
