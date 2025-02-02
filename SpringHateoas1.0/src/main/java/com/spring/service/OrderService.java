package com.spring.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.domain.entity.Client;
import com.spring.domain.entity.Order;
import com.spring.domain.entity.Product;
import com.spring.domain.entity.enums.OrderStatus;
import com.spring.domain.repository.ClientRepository;
import com.spring.domain.repository.OrderRepository;
import com.spring.domain.repository.ProductRepository;
import com.spring.rest.controller.DTO.OrderDTO;
import com.spring.rest.controller.DTO.OrderResponseDTO;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public OrderResponseDTO save(OrderDTO orderDTO) {
		
		Optional<Client> client = clientRepository.findById(orderDTO.getClient());
		
		Order order = modelMapper.map(orderDTO, Order.class);
		
		List<Product> products = orderProductsConvert(order, orderDTO.getProducts());
		
		order.setStatus(OrderStatus.WAIT_PAIMENT);
		order.setOrderDate(LocalDate.now());
		order.setClient(client.get());
		order.setProducts(products);
		
		order = repository.save(order);
		
		OrderResponseDTO response = modelMapper.map(order, OrderResponseDTO.class);
				
		System.out.println("ID PEDIDO" + order.getId());
		return response;
	}
	
	private List<Product> orderProductsConvert(Order order, List<Long> listIdProducts) {
		if(listIdProducts.isEmpty()) {
			return Collections.emptyList();
		}
		
		List<Product> products = new ArrayList<>();
		/*
		ListIdProducts
				.stream()
				.map(
						idProduct -> {
							Optional<Product> p = productRepository.findById(idProduct);
							p.get().getOrders().add(order);
							products.add(p.get());
							return null;
						}
				);
			*/
		   listIdProducts.forEach(idProduct -> {
		        Optional<Product> p = productRepository.findById(idProduct);
		        if (p.isPresent()) {
		            Product product = p.get();
		            product.getOrders().add(order);
		            products.add(product);
		        } 
		        /*else {
		            // Trate o caso em que o produto não é encontrado, por exemplo:
		            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with ID " + idProduct + " not found");
		        } */
		    });
		return products;
	}

	public Optional<Order> findById(Long id){
		return repository.findById(id);
	}
	
	public List<Order> findAll(){
		return repository.findAll();
	}
}
