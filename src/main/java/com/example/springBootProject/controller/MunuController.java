package com.example.springBootProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springBootProject.config.ResponseStructure;
import com.example.springBootProject.dto.Menu;
import com.example.springBootProject.service.MenuService;

@RestController
@RequestMapping("/menu")
public class MunuController {

	@Autowired
	private MenuService menuService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Menu>> saveMenu(Menu m){
		return menuService.saveMenu(m);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Menu>> findMenu(int id){
		return menuService.findMenu(id);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Menu>> deleteMenu(int id){
		return menuService.deleteMenu(id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Menu>> updateMenu(Menu m, int id){
		return menuService.updateMenu(m, id);
	}
}
