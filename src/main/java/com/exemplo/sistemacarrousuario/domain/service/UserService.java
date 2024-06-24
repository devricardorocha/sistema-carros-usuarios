package com.exemplo.sistemacarrousuario.domain.service;

import java.util.List;

import com.exemplo.sistemacarrousuario.domain.dto.CreateUserDTO;
import com.exemplo.sistemacarrousuario.domain.dto.GetUserDTO;

/**
 * Serviço para gerenciamento de usuários.
 */
public interface UserService {

    /**
     * Retorna uma lista de todos os usuários.
     *
     * @return uma lista de DTOs contendo informações dos usuários.
     */
    List<GetUserDTO> getAll();

    /**
     * Cria um novo usuário.
     *
     * @param user O DTO contendo as informações para criar um novo usuário.
     * @return o DTO do usuário criado.
     */
    CreateUserDTO createUser(CreateUserDTO user);
	
    /**
     * Retorna um usuário pelo ID.
     *
     * @param id O ID do usuário.
     * @return o DTO do usuário encontrado.
     */
    GetUserDTO getUserByID(Integer id);
	
}
