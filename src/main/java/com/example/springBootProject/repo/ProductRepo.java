package com.example.springBootProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springBootProject.dto.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}
