package com.exemplo.sistemacarrousuario.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

	@Schema(description = "ID do usuário")
	private Integer id;

	@Schema(description = "Nome do usuário", example = "Alice")
	private String firstName;

	@Schema(description = "Sobrenome do usuário", example = "Silva")
	private String lastName;

	@Schema(description = "Email do usuário", example = "alice.silva@example.com")
	private String email;

	@Schema(description = "Data de nascimento do usuário", example = "1992-09-30")
	private LocalDate birthday;

	@Schema(description = "Login do usuário", example = "alice_silva")
	private String login;

	@Schema(description = "Número de telefone do usuário", example = "12345678987")
	private String phone;

	@Schema(description = "Data de registro do usuário", example = "1992-09-30")
	private LocalDate creation;

	@Schema(description = "Data e hora do último login do usuário", example = "1992-09-30")
	private LocalDateTime lastLogin;

}
