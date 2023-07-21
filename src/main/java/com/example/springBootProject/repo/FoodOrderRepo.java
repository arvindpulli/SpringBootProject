package com.example.springBootProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springBootProject.dto.FoodOrder;

public interface FoodOrderRepo extends JpaRepository<FoodOrder, Integer>{

}
