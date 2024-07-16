package com.spring.rest.controller.DTO;

import java.time.LocalDate;

import com.spring.domain.entity.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
	private OrderStatus status;
	private LocalDate orderDate;
}
