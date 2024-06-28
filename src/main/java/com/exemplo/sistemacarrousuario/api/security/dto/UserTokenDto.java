package com.exemplo.sistemacarrousuario.api.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * DTO para representar o token JWT e o link do usuário.
 *
 * <p>Esta classe contém o token JWT e um link para acessar as informações do usuário logado.</p>
 */
@Data
public class UserTokenDto {

    /**
     * O token JWT.
     */
	@Schema(description = "Jwt Token")
	private String token;

    /**
     * O link para acessar informações do usuário logado.
     */
	@Schema(description = "Link para acessar informações do usuário logado")
	private UserLinkDTO user;
	
	public UserTokenDto(String token, String userLink) {
		this.token = token;
		this.user = new UserLinkDTO(userLink);
	}
	
}
