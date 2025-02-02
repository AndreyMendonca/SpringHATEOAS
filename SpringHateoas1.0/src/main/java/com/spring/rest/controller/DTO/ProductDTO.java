package com.spring.rest.controller.DTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.spring.domain.entity.hateoas.customHateoas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	private String name;
	private BigDecimal price;
	private List<customHateoas> linkss = new ArrayList<>();
}
