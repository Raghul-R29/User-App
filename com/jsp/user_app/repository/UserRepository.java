package com.jsp.user_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.user_app.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByUserEmail(String email);
	Optional<User> findByUserName(String name);
	Optional<User> findByUserPassword(String password);
	Optional<User> findByContact(String contact);
	Optional<User> findByAddress(String address);
	
}
