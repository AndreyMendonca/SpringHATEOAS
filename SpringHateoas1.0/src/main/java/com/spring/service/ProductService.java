package com.spring.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.domain.entity.Product;
import com.spring.domain.repository.ProductRepository;
import com.spring.rest.controller.DTO.ProductDTO;
import com.spring.rest.controller.DTO.ProductDTO2;

@Service
public class ProductService {
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ProductDTO save(Product product) {
		product =  repository.save(product);
		System.out.println("ID PRODUTO" + product.getId());
		return modelMapper.map(product, ProductDTO.class);
	}
	
	public ProductDTO2 save2(Product product) {
		product =  repository.save(product);
		System.out.println("ID PRODUTO" + product.getId());
		return modelMapper.map(product, ProductDTO2.class);
	}
	
	public Optional<Product> findById(Long id){
		return repository.findById(id);
	}
	
	public List<Product> findAll(){
		return repository.findAll();
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
