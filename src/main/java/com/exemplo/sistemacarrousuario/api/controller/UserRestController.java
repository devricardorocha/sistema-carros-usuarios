package com.exemplo.sistemacarrousuario.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.sistemacarrousuario.api.validator.HttpRequestValidator;
import com.exemplo.sistemacarrousuario.domain.dto.CreateUserDTO;
import com.exemplo.sistemacarrousuario.domain.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/users", consumes = "application/json", produces = "application/json")
@Tag(name = "Usuários", description = "Api de usuários do Sistema de gestão de Usuários e Carros")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpRequestValidator<CreateUserDTO> userValidator;

	@GetMapping
	@Operation(summary = "Listar usuários")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna a lista de usuários"),
			@ApiResponse(responseCode = "500", description = "Erro inesperado na aplicação") })
	public ResponseEntity<List<CreateUserDTO>> getAllUsers() {
		return new ResponseEntity<List<CreateUserDTO>>(userService.getAll(), HttpStatus.OK);
	}
	
	@PostMapping
	@Operation(summary = "Criar usuário")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Request inválido"),
			@ApiResponse(responseCode = "500", description = "Erro inesperado na aplicação") })
	public ResponseEntity<CreateUserDTO> createUser(@Valid @RequestBody CreateUserDTO body) {
		userValidator.validate(body);
		return new ResponseEntity<CreateUserDTO>(userService.createUser(body), HttpStatus.OK);
	}
}
