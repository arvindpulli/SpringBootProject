package com.example.springBootProject.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.springBootProject.dto.FoodOrder;
import com.example.springBootProject.dto.Product;
import com.example.springBootProject.repo.FoodOrderRepo;

@Repository
public class FoodOrderDao {

	@Autowired
	private FoodOrderRepo foodOrderRepo; 
	
	public FoodOrder saveFoodOrder(FoodOrder fo) {
		return foodOrderRepo.save(fo);
	}
	
	
	
	public FoodOrder findFoodOrder(int id) {
	Optional<FoodOrder> optionalFoodOrder=foodOrderRepo.findById(id);
	if(optionalFoodOrder.isPresent()) {
		return optionalFoodOrder.get();
	}return null;
	}
	
	
	
	public FoodOrder deleteFoodOrder(int id) {
		Optional<FoodOrder> optionalFoodOrder=foodOrderRepo.findById(id);
		if(optionalFoodOrder.isPresent()) {
			foodOrderRepo.delete(optionalFoodOrder.get());
			return optionalFoodOrder.get();
		}return null;
	}
	
	public FoodOrder updateFoodOrder(FoodOrder fo, int id) {
		Optional<FoodOrder> optionalFoodOrder=foodOrderRepo.findById(id);
		if(optionalFoodOrder.isPresent()) {
			fo.setFoodorderId(id);
			return foodOrderRepo.save(fo);
		}return null;
	}
	
	public List<FoodOrder> getAllOrders(){
		return foodOrderRepo.findAll();
	}
}
