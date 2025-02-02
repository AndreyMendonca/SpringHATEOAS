package com.spring.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.spring.domain.entity.Product;
import com.spring.rest.controller.DTO.ProductDTO2;
import com.spring.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	@Autowired
	private ProductService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProductDTO2 save(@RequestBody Product product) {
		return service.save2(product);
	}
	
	@GetMapping("/{id}")
	public ProductDTO2 findById(@PathVariable Long id) {
		return service.findById(id)
				.map(product -> {
					ProductDTO2 dto = modelMapper.map(product, ProductDTO2.class);
					return dto;
				})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found id: " + id));
	}
	
	@GetMapping
	public List<ProductDTO2> findAll(){
		List<Product> products = service.findAll();
		
		List<ProductDTO2> productsDTOS = new ArrayList<>();
		for(Product p : products) {
			productsDTOS.add(modelMapper.map(p, ProductDTO2.class));
		}
		
		return productsDTOS;
	}
}
