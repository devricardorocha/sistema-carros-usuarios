package com.exemplo.sistemacarrousuario.api.security.controller;

import javax.validation.Valid;

import org.apache.tomcat.websocket.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.exemplo.sistemacarrousuario.api.controller.constants.ApiPathConstants;
import com.exemplo.sistemacarrousuario.api.security.dto.JwtAuthenticationDTO;
import com.exemplo.sistemacarrousuario.api.security.dto.UserTokenDto;
import com.exemplo.sistemacarrousuario.api.security.utils.JwtTokenUtils;
import com.exemplo.sistemacarrousuario.api.validator.HttpRequestValidator;
import com.exemplo.sistemacarrousuario.domain.dto.GetUserDTO;
import com.exemplo.sistemacarrousuario.domain.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador REST para autenticação de segurança.
 */
@RestController
@Tag(name = "Autenticação", description = "Api de segurança do Sistema de gestão de Usuários e Carros")
public class SecurityController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JwtTokenUtils jwtTokenUtils;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private HttpRequestValidator<JwtAuthenticationDTO> getTokenValidator;
	
	@Autowired
	private UserService userService;
	
    /**
     * Autentica um usuário e retorna um token JWT.
     *
     * @param dto O DTO contendo as credenciais de autenticação do usuário.
     * @return ResponseEntity contendo o token JWT e o status HTTP 200.
	 *
	 * <ul>
	 *   <li>{@code 200}: Retorna o token JWT e o link para as informações do usuário</li>
	 *   <li>{@code 401}: Não autorizado</li>
	 *   <li>{@code 500}: Erro inesperado na aplicação</li>
	 * </ul>
     */
	@PostMapping(value = ApiPathConstants.SignIn.apiPath)
	@Operation(summary = "Fazer signin no sistema")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna o token JWT e o link para as informações do usuário"),
			@ApiResponse(responseCode = "401", description = "Não autorizado"),
			@ApiResponse(responseCode = "500", description = "Erro inesperado na aplicação") })
	public ResponseEntity<UserTokenDto> getToken(@Valid @RequestBody JwtAuthenticationDTO dto) {

		getTokenValidator.validate(dto);
		
		Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getLogin(),
                        dto.getPassword()
                )
        );
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails details = userDetailsService.loadUserByUsername(dto.getLogin());
		String token = jwtTokenUtils.getToken(details);
		
		userService.updateUserLastLogin(dto.getLogin());
		
		String userLink = new StringBuilder()
				.append(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString())
				.append(ApiPathConstants.Me.apiPath)
				.toString();
		
		return new ResponseEntity<UserTokenDto>(new UserTokenDto(token, userLink), HttpStatus.OK);
	}

	@GetMapping(value = ApiPathConstants.Me.apiPath)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna o usuário associado ao token"),
			@ApiResponse(responseCode = "401", description = "Não autorizado"),
			@ApiResponse(responseCode = "500", description = "Erro inesperado na aplicação") })
	public ResponseEntity<GetUserDTO> getUserFromToken(
			@Parameter(required = false, hidden = true) @RequestHeader(value = Constants.AUTHORIZATION_HEADER_NAME) String authorization) {
		String login = jwtTokenUtils.getLoginFromAuthorization(authorization);
		return new ResponseEntity<GetUserDTO>(userService.getUserByLogin(login), HttpStatus.OK);
	}
}
