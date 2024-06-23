package com.exemplo.sistemacarrousuario.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

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
	private Integer id;

	@NotEmpty
	@Schema(description = "Nome do usuário", example = "Alice")
	private String firstName;

	@NotEmpty
	@Schema(description = "Sobrenome do usuário", example = "Silva")
	private String lastName;

	@Email
	@NotEmpty
	@Schema(description = "Email do usuário", example = "alice.silva@example.com")
	private String email;

	@NotNull
	@Schema(description = "Data de nascimento do usuário", example = "1992-09-30")
	private LocalDate birthday;

	@NotEmpty
	@Schema(description = "Login do usuário", example = "alice_silva")
	private String login;

	@NotEmpty
	@Length(min = 6, max = 10)
	@Schema(description = "Senha do usuário", example = "123456")
	private String password;

	@NotEmpty
	@Length(min = 11, max = 11)
	@Schema(description = "Número de telefone do usuário", example = "12345678987")
	private String phone;

	@Schema(description = "Data de registro do usuário", example = "1992-09-30")
	private LocalDate creation;

	@Schema(description = "Data e hora do último login do usuário", example = "1992-09-30")
	private LocalDateTime lastLogin;

}
