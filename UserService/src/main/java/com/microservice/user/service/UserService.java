package com.microservice.user.service;

import com.microservice.user.entities.User;

import java.util.List;

public interface UserService {

    // Create User
    User createUser(User user);

    // get all users

    List<User> getAllUsers();

    User getUser(String userId);
}
