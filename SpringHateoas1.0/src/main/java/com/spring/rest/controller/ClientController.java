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

import com.spring.domain.entity.Client;
import com.spring.rest.controller.DTO.ClientDTO2;
import com.spring.service.ClientService;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
	
	@Autowired
	private ClientService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClientDTO2 save(@RequestBody Client client) {
		return service.save2(client);
	}
	
	@GetMapping("/{id}")
	public ClientDTO2 findById(@PathVariable Long id) {
		return service.findById(id)
				.map(client -> {
					ClientDTO2 dto = modelMapper.map(client, ClientDTO2.class);
					return dto;
				})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found id: " + id));
	}
	
	@GetMapping
	public List<ClientDTO2> findAll() {
		List<Client> clients = service.findAll();
		
		List<ClientDTO2> clientsDTOS = new ArrayList<>();
		for(Client c : clients) {
			clientsDTOS.add(modelMapper.map(c, ClientDTO2.class));
		}
		
		return clientsDTOS;
	}
}
