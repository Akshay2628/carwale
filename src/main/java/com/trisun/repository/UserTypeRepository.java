package com.trisun.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trisun.entities.UserType;

public interface UserTypeRepository extends JpaRepository<UserType, Integer> {

	Optional<UserType> findByRole(String role);
}
