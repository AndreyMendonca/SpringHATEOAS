package com.spring.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.domain.entity.Client;
import com.spring.domain.repository.ClientRepository;
import com.spring.rest.controller.DTO.ClientDTO;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ClientDTO save(Client client) {
		client =  repository.save(client);
		return modelMapper.map(client, ClientDTO.class);
	}
	
	public Optional<Client> findById(Long id){
		return repository.findById(id);
	}
	
	
}
