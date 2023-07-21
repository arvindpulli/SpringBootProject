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
import com.example.springBootProject.dto.Product;
import com.example.springBootProject.dto.User;
import com.example.springBootProject.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product p,@RequestParam int menuId){
		return productService.saveProduct(p, menuId);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Product>> findProduct(@RequestParam int id){
		return productService.findProduct(id);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Product>> deleteProduct(@RequestParam int productId,@RequestParam int menuId){
		return productService.deleteProduct(productId,menuId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestBody Product p, @RequestParam int id){
		return productService.updateProduct(p, id);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<ResponseStructure<List<Product>>> findAllProducts(){
		return productService.getAllProducts();
	}
}
