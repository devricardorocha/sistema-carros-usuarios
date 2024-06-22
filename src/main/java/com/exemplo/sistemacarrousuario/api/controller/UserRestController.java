package com.exemplo.sistemacarrousuario.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.sistemacarrousuario.domain.dto.UserDTO;
import com.exemplo.sistemacarrousuario.domain.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/users")
@Api(value = "Api de gestão de usuários")
public class UserRestController {

	@Autowired
	private UserService userService;

	@GetMapping
	@Operation(summary = "Listar usuários")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna a lista de usuários"),
			@ApiResponse(responseCode = "500", description = "Erro inesperado na aplicação") })
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		return new ResponseEntity<List<UserDTO>>(userService.getAll(), HttpStatus.OK);
	}
}
