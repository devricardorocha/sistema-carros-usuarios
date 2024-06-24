package com.exemplo.sistemacarrousuario.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object para retornar dados do usuário.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GetUserDTO {
	
	/**
	 * ID do usuário.
	 */
	@Schema(description = "ID do usuário")
	private Integer id;

	/**
	 * Nome do usuário.
	 */
	@Schema(description = "Nome do usuário", example = "Alice")
	private String firstName;

	/**
	 * Sobrenome do usuário.
	 */
	@Schema(description = "Sobrenome do usuário", example = "Silva")
	private String lastName;

	/**
	 * Email do usuário.
	 */
	@Schema(description = "Email do usuário", example = "alice.silva@example.com")
	private String email;

	/**
	 * Data de nascimento do usuário.
	 */
	@Schema(description = "Data de nascimento do usuário", example = "1992-09-30")
	private LocalDate birthday;

	/**
	 * Login do usuário.
	 */
	@Schema(description = "Login do usuário", example = "alice_silva")
	private String login;

	/**
	 * Número de telefone do usuário.
	 */
	@Schema(description = "Número de telefone do usuário", example = "12345678987")
	private String phone;

	/**
	 * Data de registro do usuário.
	 */
	@Schema(description = "Data de registro do usuário", example = "1992-09-30")
	private LocalDate created;

	/**
	 * Data e hora do último login do usuário.
	 */
	@Schema(description = "Data e hora do último login do usuário", example = "1992-09-30")
	private LocalDateTime lastLogin;
}
