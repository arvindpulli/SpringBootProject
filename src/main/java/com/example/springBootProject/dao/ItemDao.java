package com.example.springBootProject.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.springBootProject.dto.Item;
import com.example.springBootProject.dto.Product;
import com.example.springBootProject.repo.ItemRepo;

@Repository
public class ItemDao {

	@Autowired
	private ItemRepo itemRepo;
	
	public Item saveItem(Item item) {
		return itemRepo.save(item);
	}
	
	public Item findItem(int id) {
		Optional<Item> optionalItem=itemRepo.findById(id);
		if(optionalItem.isPresent()) {
			return optionalItem.get();
		}else return null;
	}
	
	public Item deleteItem(int id) {
		Optional<Item> optionalItem=itemRepo.findById(id);
		if(optionalItem.isPresent()) {
			itemRepo.delete(optionalItem.get());
			return optionalItem.get();
		}return null;
	}
	
	public Item updateItem(Item item, int id) {
		Optional<Item> optionalItem=itemRepo.findById(id);
		if(optionalItem.isPresent()) {
			item.setItemId(id);
			return itemRepo.save(item);
		}return null;

	}
	public List<Item> getAllItems(){
		return itemRepo.findAll();
	}
}
