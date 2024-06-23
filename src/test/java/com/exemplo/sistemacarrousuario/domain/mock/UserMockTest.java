package com.exemplo.sistemacarrousuario.domain.mock;

import java.time.LocalDate;

import com.exemplo.sistemacarrousuario.domain.dto.CreateUserDTO;
import com.exemplo.sistemacarrousuario.domain.entity.User;
import com.exemplo.sistemacarrousuario.domain.utils.PasswordUtils;

public abstract class UserMockTest {

	public static User userA = User.builder().id(999).firstName("Alice").lastName("Silva")
			.email("alice.silva@example.com").birthday(LocalDate.now()).login("alice_silva")
			.password(PasswordUtils.encode("12345")).phone("81123456789").build();

	public static User userB = User.builder().id(998).firstName("Bruno").lastName("Costa")
			.email("bruno.costa@example.com").birthday(LocalDate.now()).login("bruno_costa")
			.password(PasswordUtils.encode("12345")).phone("81987654321").build();

	public static CreateUserDTO createdUserA = CreateUserDTO.builder().id(999).firstName("Alice").lastName("Silva")
			.email("alice.silva@example.com").birthday(LocalDate.now()).login("alice_silva")
			.password("123456").phone("81123456789").build();

	public static CreateUserDTO createdUserAWithoutID = CreateUserDTO.builder().firstName("Alice").lastName("Silva")
			.email("alice.silva@example.com").birthday(LocalDate.now()).login("alice_silva")
			.password("123456").phone("81123456789").build();
	
}