package com.example.springBootProject.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.springBootProject.dto.User;
import com.example.springBootProject.repo.UserRepo;

@Repository
public class UserDao {

	@Autowired
	private UserRepo userRepo;
	
	
	public User saveUser(User u) {
		return userRepo.save(u);
	}
	
	
	public User findUser(int id) {
		
		Optional<User> optionalUser=userRepo.findById(id);
		if(optionalUser.isPresent()) {
			return optionalUser.get();
		}else {
			return null;
		}
	}
	
	public User deleteUser(int id) {
		Optional<User> optionalUser=userRepo.findById(id);
		if(optionalUser.isPresent()) {
			userRepo.delete(optionalUser.get());;
			return optionalUser.get();
		}else {
			return null;
		}	
	}
	
	public User updateUser(User u, int id) {
		Optional<User> optionalUser=userRepo.findById(id);
		if(optionalUser.isPresent()) {
			u.setUserId(id);
			return userRepo.save(u);
		}else {
			return null;
		}
	}
	
	public User findByEmail(String email) {
		User existingUser=userRepo.findByEmail(email);
		if(existingUser!=null) {
			return existingUser;
		}
		return null;
	}
	
	public List<User> getAllUser(){
		return userRepo.findAll();
	}
}
