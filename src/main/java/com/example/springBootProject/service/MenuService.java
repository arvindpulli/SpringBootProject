package com.example.springBootProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.springBootProject.config.ResponseStructure;
import com.example.springBootProject.dao.MenuDao;
import com.example.springBootProject.dto.Menu;
import com.example.springBootProject.exception.MenuNotFound;

@Service
public class MenuService {

	@Autowired
	private MenuDao menuDao;
	
	public ResponseEntity<ResponseStructure<Menu>> saveMenu(Menu m){
		
		ResponseStructure<Menu> structure=new ResponseStructure<Menu>();
		structure.setData(menuDao.saveMenu(m));
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setMessage("Menu has benn saved !");
		return new ResponseEntity<ResponseStructure<Menu>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Menu>> findMenu(int id){
		Menu existingMenu=menuDao.findMenu(id);
		if(existingMenu!=null) {
		ResponseStructure<Menu> structure=new ResponseStructure<Menu>();
		structure.setData(existingMenu);
		structure.setStatus(HttpStatus.FOUND.value());
		structure.setMessage("Menu has been found !");
		return new ResponseEntity<ResponseStructure<Menu>>(structure,HttpStatus.FOUND);
	}
		throw new MenuNotFound("Menu with Id "+id+" is not present");
	}
	
	public ResponseEntity<ResponseStructure<Menu>> deleteMenu(int id){
		Menu menu=menuDao.deleteMenu(id);
		if(menu!=null) {
		ResponseStructure<Menu> structure=new ResponseStructure<Menu>();
		structure.setData(menu);
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Menu has been deleted !");
		return new ResponseEntity<ResponseStructure<Menu>>(structure,HttpStatus.OK);
	}
		throw new MenuNotFound("Menu with Id "+id+" is not present");
	}
	
	public ResponseEntity<ResponseStructure<Menu>> updateMenu(Menu m, int id){
		Menu menu=menuDao.updateMenu(m, id);
		if(menu!=null) {
		ResponseStructure<Menu> structure=new ResponseStructure<Menu>();
		structure.setData(menu);
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Menu has been updated !");
		return new ResponseEntity<ResponseStructure<Menu>>(structure,HttpStatus.OK);
	}
		throw new MenuNotFound("Menu with Id "+id+" is not present");
	}
}
