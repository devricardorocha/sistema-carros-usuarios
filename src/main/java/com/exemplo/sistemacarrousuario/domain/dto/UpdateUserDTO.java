package com.exemplo.sistemacarrousuario.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object para atualizar dados do usuário.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateUserDTO {

	/**
	 * ID do usuário.
	 */
	@NotNull(message = "Missing fields")
	@Schema(description = "ID do usuário")
	private Long id;

	/**
	 * Nome do usuário.
	 */
	@NotEmpty
	@Schema(description = "Nome do usuário", example = "Alice")
	private String firstName;

	/**
	 * Sobrenome do usuário.
	 */
	@NotEmpty
	@Schema(description = "Sobrenome do usuário", example = "Silva")
	private String lastName;

	/**
	 * Email do usuário.
	 */
	@Email
	@NotEmpty
	@Schema(description = "Email do usuário", example = "alice.silva@example.com")
	private String email;
	
	/**
	 * Data de nascimento do usuário.
	 */
	@NotNull
	@Schema(description = "Data de nascimento do usuário", example = "1992-09-30")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate birthday;

	/**
	 * Login do usuário.
	 */
	@NotEmpty
	@Schema(description = "Login do usuário", example = "alice_silva")
	private String login;

	/**
	 * Senha do usuário.
	 */
	@Schema(description = "Senha do usuário", example = "123456")
	private String password;

	/**
	 * Número de telefone do usuário.
	 */
	@NotEmpty
	@Length(min = 11, max = 11)
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
