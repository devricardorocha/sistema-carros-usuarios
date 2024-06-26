package com.exemplo.sistemacarrousuario.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.exemplo.sistemacarrousuario.api.controller.constants.ApiPathConstants;
import com.exemplo.sistemacarrousuario.api.security.utils.JwtTokenUtils;
import com.exemplo.sistemacarrousuario.api.validator.HttpRequestValidator;
import com.exemplo.sistemacarrousuario.domain.dto.CreateUserDTO;
import com.exemplo.sistemacarrousuario.domain.dto.UpdateUserDTO;
import com.exemplo.sistemacarrousuario.domain.mock.UserMockTest;
import com.exemplo.sistemacarrousuario.domain.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserRestController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@MockBean
	private JwtTokenUtils jwtTokenUtils;

	@MockBean
	private HttpRequestValidator<CreateUserDTO> createUserValidator;
	
	@MockBean
	private HttpRequestValidator<UpdateUserDTO> updateUserValidator;

	@BeforeEach
	@SuppressWarnings("deprecation")
	public void beforeEach() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void shouldFetchAllUsers() throws Exception {
		doReturn(List.of(UserMockTest.userA)).when(userService).getAll();
		this.mockMvc.perform(get(ApiPathConstants.Users.apiPath).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").exists())
				.andExpect(jsonPath("$[0].id", Is.is(UserMockTest.userA.getId().intValue())));
	}

	@Test
	void shouldFetchUserByID() throws Exception {
		doReturn(UserMockTest.getUserA).when(userService).getUserByID(anyLong());
		this.mockMvc
				.perform(get(ApiPathConstants.Users.apiPath + "/{id}", UserMockTest.getUserA.getId())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("id").exists())
				.andExpect(jsonPath("id", Is.is(UserMockTest.getUserA.getId().intValue())));
	}

	@Test
	void shouldCreateUser() throws Exception {
		doReturn(UserMockTest.createdUserA)
			.when(userService).createUser(any(CreateUserDTO.class));

		String userJSON = new ObjectMapper().writeValueAsString(UserMockTest.createdUserAWithoutID);

		mockMvc.perform(post(ApiPathConstants.Users.apiPath).content(userJSON).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated()).andExpect(jsonPath("id").exists());
	}
	
	@Test
	void shouldUpdateUser() throws Exception {
		doReturn(UserMockTest.updatedUserA)
			.when(userService).updateUser(anyLong(), any(UpdateUserDTO.class));

		String userJSON = new ObjectMapper().writeValueAsString(UserMockTest.updatedUserA);

		mockMvc.perform(put(ApiPathConstants.Users.apiPath + "/{id}", UserMockTest.getUserA.getId()).content(userJSON).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(jsonPath("id").exists());
	}
	
	@Test
	void shouldDeleteUser() throws Exception {
		doNothing().when(userService).deleteUserByID(anyLong());
		mockMvc.perform(delete(ApiPathConstants.Users.apiPath + "/{id}", UserMockTest.getUserA.getId())
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNoContent());
	}

}
