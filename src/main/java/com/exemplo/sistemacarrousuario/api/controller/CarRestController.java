package com.exemplo.sistemacarrousuario.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.tomcat.websocket.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.sistemacarrousuario.api.security.utils.JwtTokenUtils;
import com.exemplo.sistemacarrousuario.api.validator.HttpRequestValidator;
import com.exemplo.sistemacarrousuario.domain.dto.CreateCarDTO;
import com.exemplo.sistemacarrousuario.domain.dto.GetCarDTO;
import com.exemplo.sistemacarrousuario.domain.service.CarService;
import com.exemplo.sistemacarrousuario.domain.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador REST para gerenciamento de carros dos usuários.
 */
@RestController
@RequestMapping(value = "/cars", consumes = "application/json", produces = "application/json")
@Tag(name = "Carros", description = "Api de carros do Sistema de gestão de Usuários e Carros")
public class CarRestController {
	
	@Autowired
	private CarService carService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtTokenUtils jwtTokenUtils;
	
	@Autowired
	private HttpRequestValidator<CreateCarDTO> createCarValidator;
	
	/**
	 * Endpoint para adicionar um carro ao usuário logado.
	 *
	 * <p>Este método lida com solicitações HTTP POST para criar um novo carro para o usuário logado no caminho "/cars".</p>
	 *
	 * @param body o objeto CreateCarDTO contendo os dados do carro a ser criado
	 * @return ResponseEntity contendo o CreateCarDTO do carro criado e o status HTTP 201 (Criado)
	 *
	 * <ul>
	 *   <li>{@code 201}: Carro adicionado  com sucesso</li>
	 *   <li>{@code 400}: Request inválido</li>
	 *   <li>{@code 401}: Não autorizado</li>
	 *   <li>{@code 500}: Erro inesperado na aplicação</li>
	 * </ul>
	 */
	@PostMapping
	@Operation(summary = "Adicionar carro ao usuário logado")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Carro adicionado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Request inválido"),
			@ApiResponse(responseCode = "401", description = "Não autorizaod"),
			@ApiResponse(responseCode = "500", description = "Erro inesperado na aplicação") })
	public ResponseEntity<CreateCarDTO> addCarToUser(@Valid @RequestBody CreateCarDTO body,
			@RequestHeader(value = Constants.AUTHORIZATION_HEADER_NAME) String authorization) {
		createCarValidator.validate(body);
		
		Long userId = userService.getUserIDByLogin(
				jwtTokenUtils.getLoginFromAuthorization(authorization));
		
		return new ResponseEntity<CreateCarDTO>(carService.addCarToUser(userId, body), HttpStatus.CREATED);
	}
	
	/**
	 * Endpoint para listar todos os carros do usuário logado.
	 *
	 * <p>Este método lida com solicitações HTTP GET para obter uma lista de todos os carros do usuário logado no caminho "/cars".</p>
	 *
	 * @return ResponseEntity contendo uma lista de GetCarDTO dos carros recuperados e o status HTTP 200 (OK)
	 *
	 * <ul>
	 *   <li>{@code 200}: Retorna a lista de carros</li>
	 *   <li>{@code 500}: Erro inesperado na aplicação</li>
	 * </ul>
	 */
	@GetMapping
	@Operation(summary = "Listar carros do usuário logado")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna a lista de carros"),
			@ApiResponse(responseCode = "500", description = "Erro inesperado na aplicação") })
	public ResponseEntity<List<GetCarDTO>> getAllUsers(@RequestHeader(value = Constants.AUTHORIZATION_HEADER_NAME) String authorization) {
		
		Long userId = userService.getUserIDByLogin(
				jwtTokenUtils.getLoginFromAuthorization(authorization));
		
		return new ResponseEntity<List<GetCarDTO>>(carService.getAllByUser(userId), HttpStatus.OK);
	}
	
	
}
