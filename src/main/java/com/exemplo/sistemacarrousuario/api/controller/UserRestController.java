package com.exemplo.sistemacarrousuario.api.controller;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.sistemacarrousuario.api.validator.HttpRequestValidator;
import com.exemplo.sistemacarrousuario.domain.dto.CreateUserDTO;
import com.exemplo.sistemacarrousuario.domain.dto.GetUserDTO;
import com.exemplo.sistemacarrousuario.domain.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador REST para gerenciamento de usuários.
 */
@RestController
@RequestMapping(value = "/users", consumes = "application/json", produces = "application/json")
@Tag(name = "Usuários", description = "Api de usuários do Sistema de gestão de Usuários e Carros")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpRequestValidator<CreateUserDTO> createUserValidator;

	/**
	 * Endpoint para listar todos os usuários.
	 *
	 * <p>Este método lida com solicitações HTTP GET para obter uma lista de todos os usuários.</p>
	 *
	 * @return ResponseEntity contendo uma lista de GetUserDTO dos usuários recuperados e o status HTTP 200 (OK)
	 *
	 * <ul>
	 *   <li>{@code 200}: Retorna a lista de usuários</li>
	 *   <li>{@code 500}: Erro inesperado na aplicação</li>
	 * </ul>
	 */
	@GetMapping
	@Operation(summary = "Listar usuários")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna a lista de usuários"),
			@ApiResponse(responseCode = "500", description = "Erro inesperado na aplicação") })
	public ResponseEntity<List<GetUserDTO>> getAllUsers() {
		return new ResponseEntity<List<GetUserDTO>>(userService.getAll(), HttpStatus.OK);
	}
	
	/**
	 * Endpoint para criar um novo usuário.
	 *
	 * <p>Este método lida com solicitações HTTP POST para criar um novo usuário.</p>
	 *
	 * @param body o objeto CreateUserDTO contendo os dados do usuário a ser criado
	 * @return ResponseEntity contendo o CreateUserDTO do usuário criado e o status HTTP 201 (Criado)
	 *
	 * <ul>
	 *   <li>{@code 201}: Usuário criado com sucesso</li>
	 *   <li>{@code 400}: Request inválido</li>
	 *   <li>{@code 500}: Erro inesperado na aplicação</li>
	 * </ul>
	 */
	@PostMapping
	@Operation(summary = "Criar usuário")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Request inválido"),
			@ApiResponse(responseCode = "500", description = "Erro inesperado na aplicação") })
	public ResponseEntity<CreateUserDTO> createUser(@Valid @RequestBody CreateUserDTO body) {
		createUserValidator.validate(body);
		return new ResponseEntity<CreateUserDTO>(userService.createUser(body), HttpStatus.OK);
	}
	
	/**
	 * Endpoint para obter um usuário pelo ID.
	 *
	 * <p>Este método lida com solicitações HTTP GET no caminho "/{id}".</p>
	 *
	 * @param id o ID do usuário a ser recuperado
	 * @return ResponseEntity contendo o GetUserDTO do usuário recuperado e o status HTTP 200 (OK)
	 *
	 * <ul>
	 *   <li>{@code 200}: Retorna o usuário</li>
	 *   <li>{@code 404}: Usuário não encontrado</li>
	 *   <li>{@code 500}: Erro inesperado na aplicação</li>
	 * </ul>
	 */
	@GetMapping("/{id}")
	@Operation(summary = "Criar usuário")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna o usuário"),
			@ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro inesperado na aplicação") })
	public ResponseEntity<GetUserDTO> getUserByID(@PathVariable Integer id) {
		GetUserDTO user = userService.getUserByID(id);
		if (Objects.isNull(user))
			return new ResponseEntity<GetUserDTO>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<GetUserDTO>(user, HttpStatus.OK);
	}
}
