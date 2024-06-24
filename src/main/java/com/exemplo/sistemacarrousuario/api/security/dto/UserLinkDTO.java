package com.exemplo.sistemacarrousuario.api.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * DTO para representar o link de acesso às informações do usuário.
 *
 * <p>Esta classe contém um link que pode ser usado para acessar informações detalhadas do usuário.</p>
 */
@Getter
public class UserLinkDTO {

    /**
     * O link para acessar informações do usuário.
     */
	@Schema(description = "Link para acessar informações do usuário")
	private String link;
	
	public UserLinkDTO(String link) {
		this.link = link;
	}
	
}
