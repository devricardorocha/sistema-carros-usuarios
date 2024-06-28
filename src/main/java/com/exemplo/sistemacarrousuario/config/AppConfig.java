package com.exemplo.sistemacarrousuario.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.exemplo.sistemacarrousuario.domain.dto.CreateUserDTO;
import com.exemplo.sistemacarrousuario.domain.dto.UpdateUserDTO;
import com.exemplo.sistemacarrousuario.domain.entity.User;

@Configuration
public class AppConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new PropertyMap<User, CreateUserDTO>() {
			@Override
			protected void configure() {
				map().setPassword(null);
			}
		});
		
		modelMapper.addMappings(new PropertyMap<User, UpdateUserDTO>() {
			@Override
			protected void configure() {
				map().setPassword(null);
			}
		});

		return modelMapper;
	}

}
