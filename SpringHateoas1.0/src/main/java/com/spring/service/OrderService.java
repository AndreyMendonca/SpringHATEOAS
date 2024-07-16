package com.spring.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.domain.entity.Order;
import com.spring.domain.entity.enums.OrderStatus;
import com.spring.domain.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Order save(Order order) {
		order.setStatus(OrderStatus.WAIT_PAIMENT);
		return repository.save(order);
	}
	
	public Optional<Order> findById(Long id){
		return repository.findById(id);
	}
}
