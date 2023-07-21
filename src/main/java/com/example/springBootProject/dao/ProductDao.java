package com.example.springBootProject.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.springBootProject.dto.Product;
import com.example.springBootProject.dto.User;
import com.example.springBootProject.repo.ProductRepo;

@Repository
public class ProductDao {

	@Autowired
	private ProductRepo productRepo;
	
	public Product saveProduct(Product p) {
		return productRepo.save(p);
	}
	
	public Product findProduct(int id) {
		Optional<Product> optionalProduct=productRepo.findById(id);
		if(optionalProduct.isPresent()) {
			return optionalProduct.get();
		}return null;
	}
	
	public Product deleteProduct(int id) {
		Optional<Product> optionalProduct=productRepo.findById(id);
		if(optionalProduct.isPresent()){
			productRepo.delete(optionalProduct.get());
			return optionalProduct.get();
		}return null;
	}
	
	public Product updateProduct(Product p, int id) {
		Optional<Product> optionalProduct=productRepo.findById(id);
		if(optionalProduct.isPresent()) {
			p.setProductId(id);
			return productRepo.save(p);
		}return null;
	}
	
	public List<Product> getAllProducts(){
		return productRepo.findAll();
	}
}
