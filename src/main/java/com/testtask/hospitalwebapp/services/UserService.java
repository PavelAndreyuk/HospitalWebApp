package com.testtask.hospitalwebapp.services;

import com.testtask.hospitalwebapp.models.User;

import java.util.List;

public interface UserService {
    User save(User user);

    User get(String name);

    List<User> getAll();
}
