package com.example.springBootProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springBootProject.config.ResponseStructure;
import com.example.springBootProject.dto.FoodOrder;
import com.example.springBootProject.dto.User;
import com.example.springBootProject.service.FoodOrderService;

@RestController
@RequestMapping("/order")
public class FoodOrderController {

	@Autowired
	private FoodOrderService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<FoodOrder>> saveFoodOrder(@RequestBody  FoodOrder fo){
		
		return service.saveFoodOrder(fo);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<FoodOrder>> findFoodOrder(@RequestParam int id){
		return service.findOrder(id);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<FoodOrder>> deleteFoodOrder(@RequestParam int id){
		return service.deleteOrder(id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<FoodOrder>> updateFoodOrder(@RequestBody FoodOrder fo, @RequestParam int id){
		return service.updateOrder(fo, id);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<ResponseStructure<List<FoodOrder>>> findAllOrders(){
		return service.getAllOrders();
	}
}
