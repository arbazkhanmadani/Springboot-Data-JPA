package com.jpa.api.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.jpa.api.entities.Product;


public interface ProductRepository extends JpaRepository<Product, Integer>{

}
