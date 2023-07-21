package com.example.springBootProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springBootProject.dto.Menu;


public interface MenuRepo extends JpaRepository<Menu, Integer>{

}
