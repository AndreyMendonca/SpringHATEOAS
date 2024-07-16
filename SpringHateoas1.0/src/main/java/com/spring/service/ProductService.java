package com.spring.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.domain.entity.Product;
import com.spring.domain.repository.ProductRepository;
import com.spring.rest.controller.DTO.ProductDTO;

@Service
public class ProductService {
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ProductDTO save(Product product) {
		product =  repository.save(product);
		return modelMapper.map(product, ProductDTO.class);
	}
	
	public Optional<Product> findById(Long id){
		return repository.findById(id);
	}
}
