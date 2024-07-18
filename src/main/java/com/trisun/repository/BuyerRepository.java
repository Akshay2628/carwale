package com.trisun.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trisun.entities.Buyer;
import com.trisun.entities.User;


public interface BuyerRepository extends JpaRepository<Buyer, Integer>{

	Optional <Buyer> findByUser(User user);


}
