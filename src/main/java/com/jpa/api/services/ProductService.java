package com.jpa.api.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.api.entities.Product;
import com.jpa.api.entities.User;
import com.jpa.api.model.ProductRepository;


@Service
public class ProductService{

	@Autowired
	private ProductRepository productRepo;

	@Transactional
	public Product saveProduct(Product pro){
		Product savedPro = productRepo.save(pro);
		return savedPro;
	}

}
