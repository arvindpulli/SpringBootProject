package com.example.springBootProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.springBootProject.config.ResponseStructure;
import com.example.springBootProject.dao.FoodOrderDao;
import com.example.springBootProject.dao.ItemDao;
import com.example.springBootProject.dao.ProductDao;
import com.example.springBootProject.dto.FoodOrder;
import com.example.springBootProject.dto.Item;
import com.example.springBootProject.dto.Product;
import com.example.springBootProject.exception.FoodOrderNotFound;
import com.example.springBootProject.exception.ItemNotFound;

@Service
public class ItemService {

	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private FoodOrderDao foodOrderDao;
	
	@Autowired
	private ProductDao productDao;
	
	public ResponseEntity<ResponseStructure<Item>> saveItem(int productId, int orderId, int quantity){
		
		Product product=productDao.findProduct(productId);
		if(product!=null) {
			FoodOrder order=foodOrderDao.findFoodOrder(orderId);
			if(order!=null) {
				
				Item i=new Item();
				i.setItemName(product.getProductName());
				i.setItemCost(product.getProductPrice());
				i.setItemQuantity(quantity);
				i.setFoodOrder(order);
				
				List<Item> items=order.getItems();
				items.add(i);
				double total=0;
				
				for(Item item:items) {
					total+=(item.getItemCost()*item.getItemQuantity());
				}
				
				
				ResponseStructure<Item> structure=new ResponseStructure<Item>();
				structure.setData(itemDao.saveItem(i));
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setMessage("Item has been added");
				
				order.setFoodorderCost(total);
				foodOrderDao.updateFoodOrder(order, orderId);
				order.setItems(items);
				
				return new ResponseEntity<ResponseStructure<Item>>(structure,HttpStatus.CREATED);
		}
			throw new ItemNotFound("Order with id "+orderId+" is not present");
		}
		throw new ItemNotFound("Product with id "+productId+" is not present");
	}
	
	
	
	public ResponseEntity<ResponseStructure<Item>> findItem(int id){
		Item existingItem=itemDao.findItem(id);
		if(existingItem!=null) {
		ResponseStructure<Item> structure=new ResponseStructure<Item>();
		structure.setData(existingItem);
		structure.setStatus(HttpStatus.FOUND.value());
		structure.setMessage("Item found Succesfully");
		
		return new ResponseEntity<ResponseStructure<Item>>(structure,HttpStatus.FOUND);
	}
		throw new ItemNotFound("Item with id "+id+" is not present");
	}
	
	
	public ResponseEntity<ResponseStructure<Item>> deleteItem(int foodOrderId, int itemId){
		FoodOrder fo=foodOrderDao.findFoodOrder(foodOrderId);
		if(fo!=null) {
			Item item=itemDao.findItem(itemId);
			if(item!=null) {
				List<Item> items=fo.getItems();
				items.remove(fo);
				fo.setItems(items);
				foodOrderDao.updateFoodOrder(fo, itemId);
				
				ResponseStructure<Item> structure=new ResponseStructure<Item>();
				structure.setData(itemDao.deleteItem(itemId));
				structure.setStatus(HttpStatus.OK.value());
				structure.setMessage("Item deleted Succesfully");
				
				return new ResponseEntity<ResponseStructure<Item>>(structure,HttpStatus.OK);	
	}
			throw new ItemNotFound("Item with id "+itemId+" is not present");
		}
		throw new FoodOrderNotFound("Order with id "+itemId+" is not present");
	}
	
	
	
	public ResponseEntity<ResponseStructure<Item>> updateItem(Item i,int id){
		Item existingItem=itemDao.findItem(id);
		if(existingItem !=null) {
			if(i.getItemName()==null) {
				i.setItemName(existingItem.getItemName());
			}
			if(i.getItemQuantity()<=0) {
				i.setItemQuantity(existingItem.getItemQuantity());
			}
			
			if(i.getItemCost()<=0) {
				i.setItemCost(existingItem.getItemCost());
			}
			if(i.getFoodOrder()==null) {
				i.setFoodOrder(existingItem.getFoodOrder());
			}
		
		
		ResponseStructure<Item> structure=new ResponseStructure<Item>();
		structure.setData(itemDao.updateItem(i, id));
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Item is Updated");
		
		return new ResponseEntity<ResponseStructure<Item>>(structure,HttpStatus.OK);
	}
	else {
		throw new ItemNotFound("Item with id "+id+" is not present");
	}
}
	
	
	public ResponseEntity<ResponseStructure<List<Item>>> getAllitems(){
		ResponseStructure<List<Item>> structure=new ResponseStructure<List<Item>>();
		structure.setData(itemDao.getAllItems());
		structure.setMessage("List of All the Items");
		structure.setStatus(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<List<Item>>>(structure,HttpStatus.FOUND);
	}
	
}
