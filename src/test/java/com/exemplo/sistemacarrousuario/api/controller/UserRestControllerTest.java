package com.exemplo.sistemacarrousuario.api.controller;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import com.exemplo.sistemacarrousuario.config.AppConfig;
import com.exemplo.sistemacarrousuario.domain.dto.CreateUserDTO;
import com.exemplo.sistemacarrousuario.domain.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
@Import(AppConfig.class)
public class UserRestControllerTest {

	private static final String USERS_PATH = "/users";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void shouldFetchAllUsers() throws Exception {
		doReturn(List.of(mock(CreateUserDTO.class))).when(userService).getAll();
		this.mockMvc.perform(get(USERS_PATH)).andExpect(status().isOk()).andExpect(jsonPath("$[0].id").exists());
	}
}
