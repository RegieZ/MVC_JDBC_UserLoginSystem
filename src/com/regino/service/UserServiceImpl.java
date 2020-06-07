package com.regino.service;

import com.regino.dao.UserDaoImpl;
import com.regino.domian.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDaoImpl userDaoImpl = new UserDaoImpl();

    @Override
    public User login(String username, String password) {
        return userDaoImpl.login(username, password);
    }

    @Override
    public boolean add(User user) {
        return userDaoImpl.add(user);
    }

    @Override
    public List<User> findAll() {
        return userDaoImpl.findAll();
    }

    @Override
    public User findById(String id) {
        return userDaoImpl.findById(id);
    }

    @Override
    public boolean update(User newUser) {
        return userDaoImpl.update(newUser);
    }

    @Override
    public boolean delete(String id) {
        return userDaoImpl.delete(id);
    }
}
