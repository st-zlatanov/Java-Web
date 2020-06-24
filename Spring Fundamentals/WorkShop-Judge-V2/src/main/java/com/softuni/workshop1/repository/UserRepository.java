package com.softuni.workshop1.repository;

import com.softuni.workshop1.model.entity.User;
import com.softuni.workshop1.model.service.UserServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String name);
}
