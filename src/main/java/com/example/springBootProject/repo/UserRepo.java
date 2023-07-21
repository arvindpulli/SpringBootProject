package com.example.springBootProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springBootProject.dto.User;

import jakarta.persistence.Query;

public interface UserRepo extends JpaRepository<User, Integer> {

	@org.springframework.data.jpa.repository.Query("select u from User u where u.userEmail=?1")
	public User findByEmail(String email);
}
