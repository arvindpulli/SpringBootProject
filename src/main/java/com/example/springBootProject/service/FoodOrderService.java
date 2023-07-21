package com.example.springBootProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.springBootProject.config.ResponseStructure;
import com.example.springBootProject.dao.FoodOrderDao;
import com.example.springBootProject.dto.FoodOrder;
import com.example.springBootProject.dto.Product;
import com.example.springBootProject.exception.FoodOrderNotFound;

@Service
public class FoodOrderService {

	@Autowired
	private FoodOrderDao foDao;
	
	public ResponseEntity<ResponseStructure<FoodOrder>> saveFoodOrder(FoodOrder fo){
		
		ResponseStructure<FoodOrder> structure=new ResponseStructure<FoodOrder>();
		structure.setData(foDao.saveFoodOrder(fo));
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setMessage("Order is Taken");
		
		return new ResponseEntity<ResponseStructure<FoodOrder>>(structure,HttpStatus.CREATED);
	}
	
	
	public ResponseEntity<ResponseStructure<FoodOrder>> findOrder(int id){
		FoodOrder existingOrder=foDao.findFoodOrder(id);
		if(existingOrder!=null) {
		ResponseStructure<FoodOrder> structure=new ResponseStructure<FoodOrder>();
		structure.setData(existingOrder);
		structure.setStatus(HttpStatus.FOUND.value());
		structure.setMessage("Order is Found");
		
		return new ResponseEntity<ResponseStructure<FoodOrder>>(structure,HttpStatus.FOUND);
	}
		throw new FoodOrderNotFound("FoodOrder with Id "+id+" is not present");
	}
	
	public ResponseEntity<ResponseStructure<FoodOrder>> deleteOrder(int id){
		FoodOrder fo=foDao.findFoodOrder(id);
		if(fo!=null) {
		ResponseStructure<FoodOrder> structure=new ResponseStructure<FoodOrder>();
		structure.setData(fo);
		structure.setMessage("Order is Deleted");
		structure.setStatus(HttpStatus.OK.value());
		
		return new ResponseEntity<ResponseStructure<FoodOrder>>(structure,HttpStatus.OK);
	}
		throw new FoodOrderNotFound("FoodOrder with Id "+id+" is not present");
	}
	
	public ResponseEntity<ResponseStructure<FoodOrder>> updateOrder(FoodOrder fo,int id){
		FoodOrder existingfo=foDao.updateFoodOrder(fo, id);
		if(existingfo!=null) {
		ResponseStructure<FoodOrder>structure=new ResponseStructure<FoodOrder>();
		structure.setData(existingfo);
		structure.setMessage("Order is updated");
		structure.setStatus(HttpStatus.OK.value());
		
		return new ResponseEntity<ResponseStructure<FoodOrder>>(structure,HttpStatus.OK);
	}
		throw new FoodOrderNotFound("FoodOrder with Id "+id+" is not present");
	}
	
	public ResponseEntity<ResponseStructure<List<FoodOrder>>> getAllOrders(){
		ResponseStructure<List<FoodOrder>> structure=new ResponseStructure<List<FoodOrder>>();
		structure.setData(foDao.getAllOrders());
		structure.setMessage("List of All the Orders");
		structure.setStatus(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<List<FoodOrder>>>(structure,HttpStatus.FOUND);
	}
}
