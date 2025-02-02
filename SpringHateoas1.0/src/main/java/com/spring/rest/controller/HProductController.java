package com.spring.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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

import com.spring.domain.entity.Product;
import com.spring.domain.entity.hateoas.customHateoas;
import com.spring.rest.controller.DTO.ProductDTO;
import com.spring.service.ProductService;

@RestController
@RequestMapping("api/hateoas/p")
public class HProductController {
	@Autowired
	private ProductService service;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProductDTO save(@RequestBody Product product) {

		ProductDTO dto = service.save(product);

		dto.getLinkss()
				.add(new customHateoas("Self",
						WebMvcLinkBuilder
								.linkTo(WebMvcLinkBuilder.methodOn(HProductController.class).findById(product.getId()))
								.toUri().toString(),
						"GET"));
		dto.getLinkss()
				.add(new customHateoas("Delete",
						WebMvcLinkBuilder
								.linkTo(WebMvcLinkBuilder.methodOn(HProductController.class).delete(product.getId()))
								.toUri().toString(),
						"DELETE"));
		dto.getLinkss()
				.add(new customHateoas("List Products", WebMvcLinkBuilder
						.linkTo(WebMvcLinkBuilder.methodOn(HProductController.class).findAll()).toUri().toString(),
						"GET"));

		return dto;
	}

	@GetMapping("/{id}")
	public ProductDTO findById(@PathVariable Long id) {
		return service.findById(id).map(product -> {
			ProductDTO dto = modelMapper.map(product, ProductDTO.class);
			dto.getLinkss()
					.add(new customHateoas("Self", WebMvcLinkBuilder
							.linkTo(WebMvcLinkBuilder.methodOn(HProductController.class).findById(product.getId()))
							.toUri().toString(), "GET"));
			dto.getLinkss()
					.add(new customHateoas("Delete", WebMvcLinkBuilder
							.linkTo(WebMvcLinkBuilder.methodOn(HProductController.class).delete(product.getId()))
							.toUri().toString(), "DELETE"));
			dto.getLinkss()
					.add(new customHateoas("List Products", WebMvcLinkBuilder
							.linkTo(WebMvcLinkBuilder.methodOn(HProductController.class).findAll()).toUri().toString(),
							"GET"));

			return dto;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found id: " + id));
	}

	@GetMapping
	public List<ProductDTO> findAll() {
		List<Product> products = service.findAll();

		List<ProductDTO> productsDTOS = new ArrayList<>();
		for (Product p : products) {
			ProductDTO dto = modelMapper.map(p, ProductDTO.class);
			dto.getLinkss()
					.add(new customHateoas("Self", WebMvcLinkBuilder
							.linkTo(WebMvcLinkBuilder.methodOn(HProductController.class).findById(p.getId()))
							.toUri().toString(), "GET"));
			dto.getLinkss()
					.add(new customHateoas("Delete", WebMvcLinkBuilder
							.linkTo(WebMvcLinkBuilder.methodOn(HProductController.class).delete(p.getId()))
							.toUri().toString(), "DELETE"));
			dto.getLinkss()
					.add(new customHateoas("List Products", WebMvcLinkBuilder
							.linkTo(WebMvcLinkBuilder.methodOn(HProductController.class).findAll()).toUri().toString(),
							"GET"));
			productsDTOS.add(dto);
		}

		return productsDTOS;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
