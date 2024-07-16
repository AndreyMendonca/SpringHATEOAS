package com.spring.service;

import java.time.LocalDate;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.domain.entity.Client;
import com.spring.domain.entity.Order;
import com.spring.domain.entity.enums.OrderStatus;
import com.spring.domain.repository.ClientRepository;
import com.spring.domain.repository.OrderRepository;
import com.spring.rest.controller.DTO.OrderDTO;
import com.spring.rest.controller.DTO.OrderResponseDTO;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public OrderResponseDTO save(OrderDTO orderDTO) {
		
		Optional<Client> client = clientRepository.findById(orderDTO.getClient());
		
		Order order = modelMapper.map(orderDTO, Order.class);
		
		order.setStatus(OrderStatus.WAIT_PAIMENT);
		order.setOrderDate(LocalDate.now());
		order.setClient(client.get());
		
		order = repository.save(order);
		
		OrderResponseDTO response = modelMapper.map(order, OrderResponseDTO.class);
				
		return response;
	}
	
	public Optional<Order> findById(Long id){
		return repository.findById(id);
	}
}
