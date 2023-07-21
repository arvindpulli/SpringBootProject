package com.example.springBootProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springBootProject.config.ResponseStructure;
import com.example.springBootProject.dto.User;
import com.example.springBootProject.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody  User u) {
		return userService.saveUser(u);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<User>> findUser(@RequestParam int id) {
		return userService.findUser(id);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<User>> deleteUser(@RequestParam int id){
		return userService.deleteUser(id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User u,@RequestParam int id){
		return userService.update(u, id);
	}
	
	@GetMapping("/login")
	public ResponseEntity<ResponseStructure<User>> loginUser(@RequestParam String email, @RequestParam String password){
		return userService.login(email, password);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<ResponseStructure<List<User>>> findAllUsers(){
		return userService.getAllUser();
	}
}
