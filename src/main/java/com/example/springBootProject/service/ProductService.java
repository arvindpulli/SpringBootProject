package com.example.springBootProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.springBootProject.config.ResponseStructure;
import com.example.springBootProject.dao.MenuDao;
import com.example.springBootProject.dao.ProductDao;
import com.example.springBootProject.dto.Menu;
import com.example.springBootProject.dto.Product;
import com.example.springBootProject.exception.MenuNotFound;
import com.example.springBootProject.exception.ProductNotFound;

@Service
public class ProductService {

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private MenuDao menuDao;
	
	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product p,int menuId){
		Menu existingMenu=menuDao.findMenu(menuId);
		p.setMenu(existingMenu);
		
		ResponseStructure<Product> structure=new ResponseStructure<Product>();
		structure.setData(productDao.saveProduct(p));
		structure.setMessage("Product has been saved !");
		structure.setStatus(HttpStatus.CREATED.value());
		
		List<Product> existingProduct=existingMenu.getProducts();
		
		existingProduct.add(p);
		existingMenu.setProducts(existingProduct);
		menuDao.updateMenu(existingMenu, menuId);
		return new ResponseEntity<ResponseStructure<Product>>(structure,HttpStatus.CREATED);
			
	}
	
	public ResponseEntity<ResponseStructure<Product>> findProduct(int id){
		Product existingProduct=productDao.findProduct(id);
		if(existingProduct!=null) {
		ResponseStructure<Product> structure=new ResponseStructure<Product>();
		structure.setData(existingProduct);
		structure.setMessage("Product has been fined !");
		structure.setStatus(HttpStatus.FOUND.value());
		
		return new  ResponseEntity<ResponseStructure<Product>>(structure,HttpStatus.FOUND);	
	}
		throw new ProductNotFound("Product with Id "+id+" is not present");
	}
	
	
	
	public ResponseEntity<ResponseStructure<Product>> deleteProduct(int productId,int menuId){
		Menu menu=menuDao.findMenu(menuId);
		if(menu!=null) {
			Product product=productDao.findProduct(productId);
			if(product!=null) {
				List<Product> products=menu.getProducts();
				products.remove(product);
				menu.setProducts(products);
				menuDao.updateMenu(menu, menuId);
					
				ResponseStructure<Product> structure=new ResponseStructure<Product>();
				structure.setData(productDao.deleteProduct(productId));
				structure.setStatus(HttpStatus.OK.value());
				structure.setMessage("Product has been deleted !");
				return new  ResponseEntity<ResponseStructure<Product>>(structure,HttpStatus.OK);
			}
			throw new ProductNotFound("Product with Id "+productId+" is not present");
		}
		throw new MenuNotFound("Menu with Id "+menuId+" is not present");
	}
	
	
	
	
	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product updatedProduct, int id){
		
		Product existingProduct=productDao.findProduct(id);
		if(existingProduct!=null) {
			if(updatedProduct.getProductName()==null) {
				updatedProduct.setProductName(existingProduct.getProductName());
			}
			if(updatedProduct.getProductPrice()<=0) {
				updatedProduct.setProductPrice(existingProduct.getProductPrice());
			}
			if(updatedProduct.getProductDescription()==null) {
				updatedProduct.setProductDescription(existingProduct.getProductDescription());
			}
			if(updatedProduct.getProductCategory()==null) {
				updatedProduct.setProductCategory(existingProduct.getProductCategory());
			}
			if(updatedProduct.getMenu()==null) {
				updatedProduct.setMenu(existingProduct.getMenu());
			}
		
		ResponseStructure<Product> structure=new ResponseStructure<Product>();
		structure.setData(productDao.updateProduct(updatedProduct, id));
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Product has been updated !");
		
		return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.OK);
		}
		else
		{
			throw new ProductNotFound("Product with Id "+id+" is not present");
		}
	}	
	
	public ResponseEntity<ResponseStructure<List<Product>>> getAllProducts(){
		ResponseStructure<List<Product>> structure=new ResponseStructure<List<Product>>();
		structure.setData(productDao.getAllProducts());
		structure.setMessage("List of All the Products");
		structure.setStatus(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure,HttpStatus.FOUND);
	}
}
