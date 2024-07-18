package com.trisun.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trisun.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByEmailId(String email);

}
    