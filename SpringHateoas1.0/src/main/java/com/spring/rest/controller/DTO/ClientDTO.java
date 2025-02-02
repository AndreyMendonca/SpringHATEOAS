package com.spring.rest.controller.DTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.spring.domain.entity.hateoas.customHateoas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO extends RepresentationModel<ClientDTO> {
	private String name;
	private String cpf;
	private String email;
	private String phone;
	private LocalDate birthday;
	private List<customHateoas> linkss = new ArrayList<>();
}
