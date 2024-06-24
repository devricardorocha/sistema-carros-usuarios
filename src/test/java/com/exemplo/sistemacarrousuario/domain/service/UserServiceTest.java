package com.exemplo.sistemacarrousuario.domain.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.exemplo.sistemacarrousuario.api.controller.exception.CustomBadRequestException;
import com.exemplo.sistemacarrousuario.api.controller.exception.ResourceNotFoundException;
import com.exemplo.sistemacarrousuario.domain.dto.CreateUserDTO;
import com.exemplo.sistemacarrousuario.domain.dto.GetUserDTO;
import com.exemplo.sistemacarrousuario.domain.dto.UpdateUserDTO;
import com.exemplo.sistemacarrousuario.domain.entity.User;
import com.exemplo.sistemacarrousuario.domain.mock.UserMockTest;
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
		when(modelMapper.map(any(User.class), eq(GetUserDTO.class))).thenReturn(mock(GetUserDTO.class));
		List<GetUserDTO> users = userService.getAll();
		assertFalse(users.isEmpty());
	}
	
	@Nested
	class CreateUserTest {
		
		@Test
		void shouldCreateUser() {
			
			User userDTO = UserMockTest.userA;
			
			when(userRepository.existsByEmail(any())).thenReturn(Boolean.FALSE);
			when(userRepository.existsByLogin(any())).thenReturn(Boolean.FALSE);
			when(userRepository.save(any(User.class))).thenReturn(userDTO);
			when(modelMapper.map(any(CreateUserDTO.class), eq(User.class))).thenReturn(mock(User.class));
			when(modelMapper.map(any(User.class), eq(CreateUserDTO.class))).thenReturn(mock(CreateUserDTO.class));
			
			CreateUserDTO user = userService.createUser(CreateUserDTO.builder().id(0).build());
			
			assertNotNull(user);
			assertEquals(0, user.getId());
			assertNull(user.getPassword());
		}
		
		@Test
		void shouldNotCreateUserWithExistingEmail() {
			lenient().when(userRepository.existsByEmail(any())).thenReturn(Boolean.TRUE);
			lenient().when(userRepository.existsByLogin(any())).thenReturn(Boolean.FALSE);
			assertThrows(CustomBadRequestException.class, () -> userService.createUser(mock(CreateUserDTO.class)), "Invalid fields");
		}
		
		@Test
		void shouldNotCreateUserWithExistingLogin() {
			lenient().when(userRepository.existsByEmail(any())).thenReturn(Boolean.FALSE);
			lenient().when(userRepository.existsByLogin(any())).thenReturn(Boolean.TRUE);
			assertThrows(CustomBadRequestException.class, () -> userService.createUser(mock(CreateUserDTO.class)), "Invalid fields");
		}
	}
	
	@Nested
	class GetUserByIDTest {

		@Test
		void shouldGetUserByID() {
			when(userRepository.findById(anyInt())).thenReturn(Optional.of(UserMockTest.userA));
			when(modelMapper.map(any(User.class), eq(GetUserDTO.class))).thenReturn(UserMockTest.getUserA);
			
			GetUserDTO user = userService.getUserByID(UserMockTest.userA.getId());
			
			assertNotNull(user);
			assertEquals(UserMockTest.userA.getId(), user.getId());
		}
		
		@Test
		void shouldNotGetUserByID() {
			when(userRepository.findById(anyInt())).thenReturn(Optional.empty());
			assertNull(userService.getUserByID(anyInt()));
		}
	}
	
	@Nested
	class UpdateUserTest {
		
		@Test
		void shouldUpdateUser() {
			when(userRepository.existsByEmail(any())).thenReturn(Boolean.FALSE);
			when(userRepository.existsByLogin(any())).thenReturn(Boolean.FALSE);
			when(userRepository.findById(any())).thenReturn(Optional.of(UserMockTest.userA));
			when(userRepository.save(any(User.class))).thenReturn(UserMockTest.userA);
			when(modelMapper.map(any(User.class), eq(UpdateUserDTO.class))).thenReturn(UserMockTest.updatedUserA);
			
			UpdateUserDTO user = userService.updateUser(UserMockTest.updatedUserA.getId(), UserMockTest.updatedUserA);
			
			assertNotNull(user);
			assertEquals(UserMockTest.updatedUserA.getId(), user.getId());
			assertEquals(UserMockTest.updatedUserA.getFirstName(), user.getFirstName());
		}
		
		@Test
		void shouldNotUpdateUserWithExistingEmail() {
			lenient().when(userRepository.existsByEmail(any())).thenReturn(Boolean.TRUE);
			lenient().when(userRepository.existsByLogin(any())).thenReturn(Boolean.FALSE);
			assertThrows(CustomBadRequestException.class, () -> userService.updateUser(0, mock(UpdateUserDTO.class)), "Invalid fields");
		}
		
		@Test
		void shouldNotUpdateUserWithExistingLogin() {
			lenient().when(userRepository.existsByEmail(any())).thenReturn(Boolean.FALSE);
			lenient().when(userRepository.existsByLogin(any())).thenReturn(Boolean.TRUE);
			assertThrows(CustomBadRequestException.class, () -> userService.updateUser(0, mock(UpdateUserDTO.class)), "Invalid fields");
		}
	}
	
	@Nested
	class DeleteUserTest {

		@Test
		void shouldDeleteUserByID() {
			when(userRepository.existsById(anyInt())).thenReturn(Boolean.FALSE);
			assertDoesNotThrow(() -> userService.deleteUserByID(UserMockTest.userA.getId()));
		}
		
		@Test
		void shouldNotDeleteUserByUnexistingID() {
			when(userRepository.existsById(anyInt())).thenReturn(Boolean.TRUE);
			assertThrows(ResourceNotFoundException.class, () -> userService.deleteUserByID(UserMockTest.userA.getId()));
		}
	}
}
