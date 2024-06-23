package com.exemplo.sistemacarrousuario.domain.service;

import java.util.List;

import com.exemplo.sistemacarrousuario.domain.dto.CreateUserDTO;

public interface UserService {

	List<CreateUserDTO> getAll();

	CreateUserDTO createUser(CreateUserDTO user);
	
}
