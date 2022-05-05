package com.testtask.hospitalwebapp.repositories;

import com.testtask.hospitalwebapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User getByName(String name);
}
