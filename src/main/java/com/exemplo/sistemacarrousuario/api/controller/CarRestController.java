package com.exemplo.sistemacarrousuario.api.controller;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.apache.tomcat.websocket.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.sistemacarrousuario.api.controller.constants.ApiPathConstants;
import com.exemplo.sistemacarrousuario.api.security.utils.JwtTokenUtils;
import com.exemplo.sistemacarrousuario.api.validator.HttpRequestValidator;
import com.exemplo.sistemacarrousuario.domain.dto.CreateCarDTO;
import com.exemplo.sistemacarrousuario.domain.dto.GetCarDTO;
import com.exemplo.sistemacarrousuario.domain.dto.UpdateCarDTO;
import com.exemplo.sistemacarrousuario.domain.service.CarService;
import com.exemplo.sistemacarrousuario.domain.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador REST para gerenciamento de carros dos usuários.
 */
@RestController
@RequestMapping(value = ApiPathConstants.Cars.apiPath)
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
	
	@Autowired
	private HttpRequestValidator<UpdateCarDTO> updateCarValidator;
	
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
			@ApiResponse(responseCode = "401", description = "Não autorizado"),
			@ApiResponse(responseCode = "500", description = "Erro inesperado na aplicação") })
	public ResponseEntity<CreateCarDTO> addCarToUser(@Valid @RequestBody CreateCarDTO body,
			@Parameter(required = false, hidden = true) @RequestHeader(value = Constants.AUTHORIZATION_HEADER_NAME) String authorization) {
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
	 *   <li>{@code 401}: Não autorizado</li>
	 *   <li>{@code 500}: Erro inesperado na aplicação</li>
	 * </ul>
	 */
	@GetMapping
	@Operation(summary = "Listar carros do usuário logado")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna a lista de carros"),
			@ApiResponse(responseCode = "401", description = "Não autorizado"),
			@ApiResponse(responseCode = "500", description = "Erro inesperado na aplicação") })
	public ResponseEntity<List<GetCarDTO>> getAllCarsByUser(
			@Parameter(required = false, hidden = true) @RequestHeader(value = Constants.AUTHORIZATION_HEADER_NAME) String authorization) {
		
		Long userId = userService.getUserIDByLogin(
				jwtTokenUtils.getLoginFromAuthorization(authorization));
		
		return new ResponseEntity<List<GetCarDTO>>(carService.getAllByUser(userId), HttpStatus.OK);
	}
	
	/**
	 * Retorna o carro do usuário logado pelo ID do carro.
	 *
	 * @param id o ID do carro
	 * @param authorization o token de autorização JWT do usuário logado
	 * @return uma ResponseEntity contendo o DTO do carro e o status HTTPHTTP 200 (OK)
	 *
	 * <ul>
	 *   <li>{@code 200}: Retorna o carro</li>
	 *   <li>{@code 401}: Não autorizado</li>
	 *   <li>{@code 404}: Carro não encontrado</li>
	 *   <li>{@code 500}: Erro inesperado na aplicação</li>
	 * </ul>
	 */
	@GetMapping("/{id}")
	@Operation(summary = "Retorna carro do usuário logado")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna o carro"),
			@ApiResponse(responseCode = "401", description = "Não autorizado"),
			@ApiResponse(responseCode = "404", description = "Carro não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro inesperado na aplicação") })
	public ResponseEntity<GetCarDTO> getCarByIdAndUser(@PathVariable Long id,
			@Parameter(required = false, hidden = true) @RequestHeader(value = Constants.AUTHORIZATION_HEADER_NAME) String authorization) {
		
		Long userId = userService.getUserIDByLogin(
				jwtTokenUtils.getLoginFromAuthorization(authorization));
		
		GetCarDTO car = carService.getCarByIDAndUserByID(id, userId);

		if (Objects.isNull(car))
			return new ResponseEntity<GetCarDTO>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<GetCarDTO>(car, HttpStatus.OK);
	}
	
	/**
	 * Endpoint para deletar um carro do usuário pelo ID.
	 *
	 * <p>Este método lida com solicitações HTTP DELETE no caminho "/cars/{id}".</p>
	 *
	 * @param id o ID do carro a ser deletado
	 * @return ResponseEntity com o status HTTP 204 (No Content)
	 *
	 * <ul>
	 *   <li>{@code 204}: Carro removido com sucesso</li>
	 *   <li>{@code 404}: Carro não encontrado</li>
	 *   <li>{@code 500}: Erro inesperado na aplicação</li>
	 * </ul>
	 */
	@DeleteMapping("/{id}")
	@Operation(summary = "Remover carro")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Carro removido com sucesso"),
			@ApiResponse(responseCode = "404", description = "Carro não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro inesperado na aplicação") })
	public ResponseEntity<Void> deleteUser(@PathVariable Long id,
			@Parameter(required = false, hidden = true) @RequestHeader(value = Constants.AUTHORIZATION_HEADER_NAME) String authorization) {

		Long userId = userService.getUserIDByLogin(
				jwtTokenUtils.getLoginFromAuthorization(authorization));

		carService.deleteCarByIDAndUserID(id, userId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * Endpoint para atualizar um carro existente do usuário logado pelo ID.
	 *
	 * <p>Este método lida com solicitações HTTP PUT no caminho "/cars/{id}".</p>
	 *
	 * @param id o ID do carro a ser atualizado
	 * @param body o objeto UpdateCarDTO contendo os novos dados do carro
	 * @return ResponseEntity contendo o UpdateCarDTO do carro atualizado e o status HTTP 200 (OK)
	 *
	 * <ul>
	 *   <li>{@code 200}: Usuário atualizado com sucesso</li>
	 *   <li>{@code 400}: Request inválido</li>
	 *   <li>{@code 500}: Erro inesperado na aplicação</li>
	 * </ul>
	 */
	@PutMapping("/{id}")
	@Operation(summary = "Atualizar carro")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Carro atualizado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Request inválido"),
			@ApiResponse(responseCode = "404", description = "Carro não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro inesperado na aplicação") })
	public ResponseEntity<UpdateCarDTO> createUser(@PathVariable Long id, @Valid @RequestBody UpdateCarDTO body,
			@Parameter(required = false, hidden = true) @RequestHeader(value = Constants.AUTHORIZATION_HEADER_NAME) String authorization) {
		
		updateCarValidator.validate(body);
		Long userId = userService.getUserIDByLogin(jwtTokenUtils.getLoginFromAuthorization(authorization));
		
		return new ResponseEntity<UpdateCarDTO>(carService.updateCarByUser(id, userId, body), HttpStatus.OK);
	}
}
