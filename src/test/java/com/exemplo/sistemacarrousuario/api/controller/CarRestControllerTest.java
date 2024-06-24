package com.exemplo.sistemacarrousuario.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.exemplo.sistemacarrousuario.api.security.utils.JwtTokenUtils;
import com.exemplo.sistemacarrousuario.api.validator.HttpRequestValidator;
import com.exemplo.sistemacarrousuario.domain.dto.CreateCarDTO;
import com.exemplo.sistemacarrousuario.domain.mock.CarMockTest;
import com.exemplo.sistemacarrousuario.domain.service.CarService;
import com.exemplo.sistemacarrousuario.domain.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CarRestController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CarRestControllerTest {

	private static final String CARS_PATH = "/cars";

	@Value("${jwt.bearer.authorization}")
	private String authorization;
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CarService carService;

	@MockBean
	private UserService userService;
	
	@MockBean
	private JwtTokenUtils jwtTokenUtils;

	@MockBean
	private HttpRequestValidator<CreateCarDTO> createCarValidator;

	@BeforeEach
	@SuppressWarnings("deprecation")
	public void beforeEach() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void shouldCreateUser() throws Exception {
		doReturn(CarMockTest.createdCarA)
			.when(carService).addCarToUser(anyLong(), any(CreateCarDTO.class));

		String userJSON = new ObjectMapper().writeValueAsString(CarMockTest.createdCarA);

		mockMvc.perform(post(CARS_PATH).header(HttpHeaders.AUTHORIZATION, authorization).content(userJSON)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated())
				.andExpect(jsonPath("id").exists());
	}
}
