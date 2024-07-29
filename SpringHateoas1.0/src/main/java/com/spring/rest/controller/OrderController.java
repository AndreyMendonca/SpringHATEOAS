package com.spring.rest.controller;

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

import com.spring.rest.controller.DTO.OrderDTO;
import com.spring.rest.controller.DTO.OrderResponseDTO;
import com.spring.service.OrderService;

@RestController
@RequestMapping("api/orders")
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrderResponseDTO save(@RequestBody OrderDTO orderDTO) {
		return service.save(orderDTO);
	}
	
	@GetMapping("/{id}")
	public OrderResponseDTO findById(@PathVariable Long id) {
		return service.findById(id)
				.map(order->{
					OrderResponseDTO dto = modelMapper.map(order, OrderResponseDTO.class);
					return dto;
				})
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not exist"));
	}
	
}
