package com.realProject.repository;

import com.realProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmailId(String emailId);
    Optional<User> findByMobileNumber(String mobileNumber);
}