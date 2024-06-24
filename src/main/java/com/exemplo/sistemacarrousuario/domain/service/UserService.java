package com.exemplo.sistemacarrousuario.domain.service;

import java.util.List;

import com.exemplo.sistemacarrousuario.domain.dto.CreateUserDTO;
import com.exemplo.sistemacarrousuario.domain.dto.GetUserDTO;
import com.exemplo.sistemacarrousuario.domain.dto.UpdateUserDTO;

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
    GetUserDTO getUserByID(Long id);
    
    /**
     * Atualiza as informações de um usuário existente.
     *
     * @param user O DTO contendo as novas informações do usuário a serem atualizadas.
     * @return O DTO atualizado do usuário.
     */
    UpdateUserDTO updateUser(Long id, UpdateUserDTO user);
	
    /**
     * Remove um usuário pelo ID.
     *
     * <p>Este método deleta um usuário existente com base no ID fornecido.</p>
     *
     * @param id o ID do usuário a ser removido
     */
    void deleteUserByID(Long id);

    /**
     * Obter um usuário pelo login.
     *
     * <p>Este método retorna um usuário existente pelo login fornecido.</p>
     *
     * @param login o login do usuário
     */
    GetUserDTO getUserByLogin(String login);

    /**
     * Atualiza o último login do usuário.
     *
     * <p>Este método atualiza o último login do usuário pelo login fornecido.</p>
     *
     * @param login o login do usuário
     */
	void updateUserLastLogin(String login);
	
    /**
     * Obter um ID de usuário pelo login.
     *
     * <p>Este método retorna um ID do usuário existente pelo login fornecido.</p>
     *
     * @param login o login do usuário
     */
	Long getUserIDByLogin(String login);
}
