package com.testtask.hospitalwebapp.services;

import com.testtask.hospitalwebapp.models.User;
import com.testtask.hospitalwebapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User save(@NotNull User user) {
        if (user == null) throw new NullPointerException("User required");

        return userRepository.save(user);
    }

    @Override
    public User get(String name) {
        return userRepository.getByName(name);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
