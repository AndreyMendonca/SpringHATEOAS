package com.spring.rest.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.spring.domain.entity.Client;
import com.spring.rest.controller.DTO.ClientDTO;
import com.spring.service.ClientService;

@RestController
@RequestMapping("api/hateoas/c")
public class HClientController {
	@Autowired
	private ClientService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public ClientDTO save(@RequestBody Client client) {
		ClientDTO dto = service.save(client);
		dto.add(linkTo(methodOn(HClientController.class).findById(client.getId())).withSelfRel());
		dto.add(linkTo(methodOn(HClientController.class).findAll()).withRel("Clients List"));
		dto.add(linkTo(methodOn(HClientController.class).delete(client.getId())).withRel("Delete Client"));
		return dto;
	}
	
	@GetMapping("/{id}")
	public ClientDTO findById(@PathVariable Long id) {
		return service.findById(id)
				.map(client -> {
					ClientDTO dto = modelMapper.map(client, ClientDTO.class);
					dto.add(linkTo(methodOn(HClientController.class).findById(client.getId())).withSelfRel());
					dto.add(linkTo(methodOn(HClientController.class).findAll()).withRel("Clients List"));
					dto.add(linkTo(methodOn(HClientController.class).delete(client.getId())).withRel("Delete Client"));
					return dto;
				})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found id: " + id));
	}
	
	@GetMapping()
	public List<ClientDTO> findAll(){
		List<Client> clients = service.findAll();
		List<ClientDTO> clientsDTO = new ArrayList<>();
		for(Client c : clients) {
			long id = c.getId();
			ClientDTO dto = (modelMapper.map(c, ClientDTO.class));
			dto.add(linkTo(methodOn(HClientController.class).findById(id)).withSelfRel());
			clientsDTO.add(dto);
		}
		return clientsDTO;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
