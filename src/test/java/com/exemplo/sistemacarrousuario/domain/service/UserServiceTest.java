package com.exemplo.sistemacarrousuario.domain.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.exemplo.sistemacarrousuario.domain.dto.UserDTO;
import com.exemplo.sistemacarrousuario.domain.entity.User;
import com.exemplo.sistemacarrousuario.domain.repository.UserRepository;
import com.exemplo.sistemacarrousuario.domain.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	private UserRepository userRepository;

	@Mock
	private ModelMapper modelMapper;

	@Test
	void shouldFetchAllUsers() {
		when(userRepository.findAll()).thenReturn(List.of(mock(User.class)));
		when(modelMapper.map(any(User.class), eq(UserDTO.class))).thenReturn(mock(UserDTO.class));
		List<UserDTO> users = userService.getAll();
		assertFalse(users.isEmpty());
	}
}
