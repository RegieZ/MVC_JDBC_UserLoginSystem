package com.regino.service;

import com.regino.domian.User;

import java.util.List;

public interface UserService {

    // Login
    public abstract User login(String username, String password);

    // Create
    public abstract boolean add(User user);

    // Retrieve all
    public abstract List<User> findAll();

    // Retrieve
    public abstract User findById(String id);

    // Update
    public abstract boolean update(User newUser);

    // Delete
    public abstract boolean delete(String id);
}
