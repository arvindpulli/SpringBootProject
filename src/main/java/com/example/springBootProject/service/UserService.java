package com.example.springBootProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.springBootProject.config.ResponseStructure;
import com.example.springBootProject.dao.UserDao;
import com.example.springBootProject.dto.User;
import com.example.springBootProject.exception.PasswordNotMatch;
import com.example.springBootProject.exception.UserNotFound;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public ResponseEntity<ResponseStructure<User>> saveUser(User u){
		
		ResponseStructure<User> structure =new ResponseStructure<User>();
		structure.setMessage("User has been saved !");
		
		structure.setData(userDao.saveUser(u));
		
		structure.setStatus(HttpStatus.CREATED.value());
		
		return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.CREATED);
		
	}
	
	public ResponseEntity<ResponseStructure<User>> findUser(int id){
		User existingUser=userDao.findUser(id);
		if(existingUser!=null) {
		ResponseStructure<User> structure =new ResponseStructure<User>();
		structure.setMessage("User has been found !");
		
		structure.setData(existingUser);
		
		structure.setStatus(HttpStatus.FOUND.value());
		
		return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.FOUND);
	}
		throw new UserNotFound("User with Id "+id+" is not present");
	}
	
	public ResponseEntity<ResponseStructure<User>> deleteUser(int id){
		User  existingUser=userDao.deleteUser(id);
		if(existingUser!=null) {
		ResponseStructure<User> structure=new ResponseStructure<User>();
		structure.setMessage("User deleted Succesfully !");
		
		structure.setStatus(HttpStatus.OK.value());
		
		structure.setData(existingUser);
		
		return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.OK);	
	}
		throw new UserNotFound("User with Id "+id+" is not present");
	}
	
	public ResponseEntity<ResponseStructure<User>> update(User updatedUser,int id){
		
		User existingUser=userDao.findUser(id);
		if(existingUser!=null) {
			if(updatedUser.getUserName()==null) {
				updatedUser.setUserName(existingUser.getUserName());
			}
			if(updatedUser.getUserEmail()==null) {
				updatedUser.setUserEmail(existingUser.getUserEmail());
			}
			if(updatedUser.getUserAddress()==null) {
				updatedUser.setUserAddress(existingUser.getUserAddress());
			}
			if(updatedUser.getUserPhone()<=999999999) {
				updatedUser.setUserPhone(existingUser.getUserPhone());
			}
			if(updatedUser.getUserPassword()==null) {
				updatedUser.setUserPassword(existingUser.getUserPassword());
			}
		
		ResponseStructure<User> structure=new ResponseStructure<User>();
		
		structure.setMessage("User Modified Succesfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(userDao.updateUser(updatedUser, id));
		
		return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.OK); 
		}
		throw new UserNotFound("User with Id "+id+" is not present");
	}
	
	//findByEmail-->as login
	public ResponseEntity<ResponseStructure<User>> login(String email,String Password){
		User existingUser=userDao.findByEmail(email);
		if(existingUser!=null) {
			String userPassword=existingUser.getUserPassword();
			if(userPassword.equals(Password)) {
				ResponseStructure<User> structure=new ResponseStructure<User>();
				structure.setData(existingUser);
				structure.setMessage("Login Success");
				structure.setStatus(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.OK);
			}
			throw new PasswordNotMatch("Entered password is incorrect! please try again. ");
		}
		throw new UserNotFound("No user found with entered email");	
	}
	
	public ResponseEntity<ResponseStructure<List<User>>> getAllUser(){
		ResponseStructure<List<User>> structure=new ResponseStructure<List<User>>();
		structure.setData(userDao.getAllUser());
		structure.setMessage("List of all the User");
		structure.setStatus(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<List<User>>>(structure,HttpStatus.FOUND);
	}
	
}
