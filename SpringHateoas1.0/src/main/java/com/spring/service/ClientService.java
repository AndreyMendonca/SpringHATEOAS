package com.spring.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.domain.entity.Client;
import com.spring.domain.repository.ClientRepository;
import com.spring.rest.controller.DTO.ClientDTO;
import com.spring.rest.controller.DTO.ClientDTO2;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ClientDTO save(Client client) {
		client =  repository.save(client);
		System.out.println("ID CLIENTE" + client.getId());
		return modelMapper.map(client, ClientDTO.class);
	}
	
	public ClientDTO2 save2(Client client) {
		client =  repository.save(client);
		System.out.println("ID CLIENTE" + client.getId());
		return modelMapper.map(client, ClientDTO2.class);
	}
	
	public Optional<Client> findById(Long id){
		return repository.findById(id);
	}
	
	public List<Client> findAll(){
		return repository.findAll();
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
}
