package com.example.springBootProject.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Component
public class FoodOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int foodorderId;
	
	@NotNull(message = "Location can't be null")
	@NotBlank(message = "Location can't be blank")
	private String foodorderLocation;
	
	
	private double foodorderCost;
	
	
	@Autowired
	@OneToMany
	private List<Item> items;

	public int getFoodorderId() {
		return foodorderId;
	}

	public void setFoodorderId(int foodorderId) {
		this.foodorderId = foodorderId;
	}

	public String getFoodorderLocation() {
		return foodorderLocation;
	}

	public void setFoodorderLocation(String foodorderLocation) {
		this.foodorderLocation = foodorderLocation;
	}

	public double getFoodorderCost() {
		return foodorderCost;
	}

	public void setFoodorderCost(double foodorderCost) {
		this.foodorderCost = foodorderCost;
	}

	

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	
	
}
