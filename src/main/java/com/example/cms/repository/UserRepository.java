package com.example.cms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cms.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	boolean existsByEmail(String userEmail);
	
	Optional<User> findByEmail(String email);

}
