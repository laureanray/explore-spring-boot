package com.laureanray.exploresb.services;

import java.util.List;

import com.laureanray.exploresb.model.User;

public interface UserService {
    User findById(long userId);
    List<User> findAllUsers();
    User createUser(User user);
    User updateUser(User user, long userId);
    User deleteUser(long userId);
}
