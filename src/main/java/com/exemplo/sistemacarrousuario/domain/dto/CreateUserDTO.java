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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateUserDTO {

	@Schema(description = "ID do usuário")
	private Long id;

	@NotEmpty
	@Length(min = 1, max = 255)
	@Schema(description = "Nome do usuário", example = "Alice", minLength = 1, maxLength = 255)
	private String firstName;

	@NotEmpty
	@Length(min = 1, max = 255)
	@Schema(description = "Sobrenome do usuário", example = "Silva", minLength = 1, maxLength = 255)
	private String lastName;

	@Email
	@NotEmpty
	@Length(min = 1, max = 255)
	@Schema(description = "Email do usuário", example = "alice.silva@example.com", minLength = 1, maxLength = 255)
	private String email;

	@NotNull
	@Schema(description = "Data de nascimento do usuário", example = "1992-09-30")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate birthday;

	@NotEmpty
	@Length(min = 1, max = 255)
	@Schema(description = "Login do usuário", example = "alice_silva", minLength = 1, maxLength = 255)
	private String login;

	@NotEmpty
	@Length(min = 6, max = 10)
	@Schema(description = "Senha do usuário", example = "123456", minLength = 6, maxLength = 10)
	private String password;

	@NotEmpty
	@Length(min = 10, max = 11)
	@Schema(description = "Número de telefone do usuário", example = "12345678987", minLength = 10, maxLength = 11)
	private String phone;

	@Schema(description = "Data de registro do usuário", example = "1992-09-30")
	private LocalDate created;

	@Schema(description = "Data e hora do último login do usuário", example = "1992-09-30")
	private LocalDateTime lastLogin;

}
