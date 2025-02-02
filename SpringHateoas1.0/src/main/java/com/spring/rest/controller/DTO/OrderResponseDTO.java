package com.spring.rest.controller.DTO;

import java.time.LocalDate;
import java.util.List;

import com.spring.domain.entity.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
	private OrderStatus status;
	private LocalDate orderDate;
	private ClientDTO2 client;
	private List<ProductDTO> products;
}
