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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.exemplo.sistemacarrousuario.api.controller.constants.ApiPathConstants;
import com.exemplo.sistemacarrousuario.api.security.utils.JwtTokenUtils;
import com.exemplo.sistemacarrousuario.api.validator.HttpRequestValidator;
import com.exemplo.sistemacarrousuario.domain.dto.CreateCarDTO;
import com.exemplo.sistemacarrousuario.domain.dto.UpdateCarDTO;
import com.exemplo.sistemacarrousuario.domain.mock.CarMockTest;
import com.exemplo.sistemacarrousuario.domain.mock.UserMockTest;
import com.exemplo.sistemacarrousuario.domain.service.CarService;
import com.exemplo.sistemacarrousuario.domain.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CarRestController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CarRestControllerTest {

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

	@MockBean
	private HttpRequestValidator<UpdateCarDTO> updateCarValidator;

	@BeforeEach
	@SuppressWarnings("deprecation")
	public void beforeEach() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void shouldFetchAllCars() throws Exception {
		doReturn(List.of(CarMockTest.carA)).when(carService).getAllByUser(anyLong());
		this.mockMvc
				.perform(get(ApiPathConstants.Cars.apiPath).header(HttpHeaders.AUTHORIZATION, authorization)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].id").exists())
				.andExpect(jsonPath("$[0].id", Is.is(CarMockTest.carA.getId().intValue())));
	}
	
	@Test
	void shouldCreateUser() throws Exception {
		doReturn(CarMockTest.createdCarA)
			.when(carService).addCarToUser(anyLong(), any(CreateCarDTO.class));

		String userJSON = new ObjectMapper().writeValueAsString(CarMockTest.createdCarA);

		mockMvc.perform(post(ApiPathConstants.Cars.apiPath).header(HttpHeaders.AUTHORIZATION, authorization).content(userJSON)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated())
				.andExpect(jsonPath("id").exists());
	}
	
	@Test
	void shouldFetchUserByID() throws Exception {
		doReturn(CarMockTest.getCarA).when(carService).getCarByIDAndUserByID(anyLong(), anyLong());
		this.mockMvc
				.perform(get(ApiPathConstants.Cars.apiPath + "/{id}", CarMockTest.carA.getId()).header(HttpHeaders.AUTHORIZATION, authorization)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("id").exists())
				.andExpect(jsonPath("id", Is.is(CarMockTest.carA.getId().intValue())));
	}
	
	@Test
	void shouldDeleteCar() throws Exception {
		doNothing().when(carService).deleteCarByIDAndUserID(anyLong(), anyLong());
		mockMvc.perform(delete(ApiPathConstants.Cars.apiPath + "/{id}", UserMockTest.getUserA.getId()).header(HttpHeaders.AUTHORIZATION, authorization)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNoContent());
	}
	
	@Test
	void shouldUpdateUser() throws Exception {
		doReturn(CarMockTest.updatedCarA)
			.when(carService).updateCarByUser(anyLong(), anyLong(), any(UpdateCarDTO.class));

		String userJSON = new ObjectMapper().writeValueAsString(CarMockTest.updatedCarA);

		mockMvc.perform(put(ApiPathConstants.Cars.apiPath + "/{id}", CarMockTest.getCarA.getId()).content(userJSON)
				.contentType(MediaType.APPLICATION_JSON_VALUE).header(HttpHeaders.AUTHORIZATION, authorization))
				.andExpect(status().isOk()).andExpect(jsonPath("id").exists());
	}
	
}
