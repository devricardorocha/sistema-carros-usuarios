package com.exemplo.sistemacarrousuario.api.security.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class JwtAuthenticationDto {

	@NotEmpty
	private String login;

	@NotEmpty
	private String senha;

}
