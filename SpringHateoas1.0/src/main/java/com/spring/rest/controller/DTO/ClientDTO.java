package com.spring.rest.controller.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
	private String name;
	private String cpf;
	private String email;
	private String phone;
	private LocalDate birthday;
}
