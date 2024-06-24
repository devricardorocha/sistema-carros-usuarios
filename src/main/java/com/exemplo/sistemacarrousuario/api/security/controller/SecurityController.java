package com.exemplo.sistemacarrousuario.api.security.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.exemplo.sistemacarrousuario.api.security.dto.JwtAuthenticationDTO;
import com.exemplo.sistemacarrousuario.api.security.dto.UserTokenDto;
import com.exemplo.sistemacarrousuario.api.security.utils.JwtTokenUtils;
import com.exemplo.sistemacarrousuario.api.validator.HttpRequestValidator;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * Controlador REST para autenticação de segurança.
 */
@RestController
@RequestMapping(value = "/signin", consumes = "application/json", produces = "application/json")
public class SecurityController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JwtTokenUtils jwtTokenUtils;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private HttpRequestValidator<JwtAuthenticationDTO> getTokenValidator;
	
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
	@PostMapping
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
		
		String userLink = new StringBuilder()
				.append(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString())
				.append("/me")
				.toString();
		
		return new ResponseEntity<UserTokenDto>(new UserTokenDto(token, userLink), HttpStatus.OK);
	}
}
