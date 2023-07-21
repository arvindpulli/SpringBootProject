package com.example.springBootProject.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.springBootProject.dto.Menu;
import com.example.springBootProject.dto.Product;
import com.example.springBootProject.repo.MenuRepo;
import com.example.springBootProject.repo.UserRepo;

@Repository
public class MenuDao {

	@Autowired
	private MenuRepo menuRepo;
	
	public Menu saveMenu(Menu m) {
		
		return menuRepo.save(m);
	}
	
	public Menu findMenu(int id) {
		Optional<Menu> optionalMenu=menuRepo.findById(id);
		if(optionalMenu.isPresent())
		{
			return optionalMenu.get();
		}
		else
			return null;
	}
	
	public Menu deleteMenu(int id) {
		Optional<Menu> optionalMenu=menuRepo.findById(id);
		if(optionalMenu.isPresent()) {
			menuRepo.delete(optionalMenu.get());
			return optionalMenu.get();
		}
		return null;
	}
	
	public Menu updateMenu(Menu m, int id) {
		Optional<Menu> optionalMenu=menuRepo.findById(id);
		if(optionalMenu.isPresent()) {
			m.setMenuId(id);
			return menuRepo.save(m);
		}return null;
	}
	
	
}
