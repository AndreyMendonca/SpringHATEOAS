package com.spring.domain.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelConfigurarion {
	
	@Bean
	public ModelMapper getModel() {
		return new ModelMapper();
	}
}
