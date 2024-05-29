package com.example.userProfileGenerator.repository;

import com.example.userProfileGenerator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

