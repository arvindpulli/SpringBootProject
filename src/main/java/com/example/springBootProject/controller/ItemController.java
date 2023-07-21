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
import com.example.springBootProject.dto.Item;
import com.example.springBootProject.dto.User;
import com.example.springBootProject.service.ItemService;

@RestController
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Item>> saveItem(@RequestParam int productId,@RequestParam int orderId,@RequestParam int quantity){
		return service.saveItem(productId, orderId, quantity);
	}
	
	

	@GetMapping
	public ResponseEntity<ResponseStructure<Item>> findItem(@RequestParam int id){
		return service.findItem(id);
	}
	
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Item>> deleteItem(@RequestParam int foodOrderId,@RequestParam int itemId){
		return service.deleteItem(foodOrderId, itemId);
	}
	
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Item>> updateItem(@RequestBody Item i,  @RequestParam int id){
		return service.updateItem(i, id);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<ResponseStructure<List<Item>>> findAllItems(){
		return service.getAllitems();
	}
	
}
