package com.example.springBootProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springBootProject.dto.Item;

public interface ItemRepo extends JpaRepository<Item, Integer>{

}
