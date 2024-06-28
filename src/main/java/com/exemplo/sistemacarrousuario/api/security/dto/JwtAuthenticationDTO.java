package com.exemplo.sistemacarrousuario.api.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * DTO para representar as credenciais de autenticação JWT.
 *
 * <p>Esta classe contém o login e a senha do usuário, necessários para autenticação.</p>
 */
@Data
public class JwtAuthenticationDTO {

    /**
     * O login do usuário.
     */
	@NotEmpty
	@Schema(description = "Login do usuário")
	private String login;

    /**
     * A senha do usuário.
     */
	@NotEmpty
	@Schema(description = "Senha do usuário")
	private String password;

}
