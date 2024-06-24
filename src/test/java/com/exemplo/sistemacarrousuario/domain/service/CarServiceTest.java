package com.exemplo.sistemacarrousuario.domain.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
import com.exemplo.sistemacarrousuario.domain.dto.CreateCarDTO;
import com.exemplo.sistemacarrousuario.domain.dto.GetCarDTO;
import com.exemplo.sistemacarrousuario.domain.dto.UpdateCarDTO;
import com.exemplo.sistemacarrousuario.domain.entity.Car;
import com.exemplo.sistemacarrousuario.domain.mock.CarMockTest;
import com.exemplo.sistemacarrousuario.domain.mock.UserMockTest;
import com.exemplo.sistemacarrousuario.domain.repository.CarRepository;
import com.exemplo.sistemacarrousuario.domain.repository.UserRepository;
import com.exemplo.sistemacarrousuario.domain.service.impl.CarServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

	@InjectMocks
	private CarServiceImpl carService;

	@Mock
	private CarRepository carRepository;
	
	@Mock
	private UserRepository userRepository;

	@Mock
	private ModelMapper modelMapper;
	
	@Test
	void shouldFetchAllCars() {
		when(carRepository.findByUserId(anyLong())).thenReturn(List.of(mock(Car.class)));
		when(modelMapper.map(any(Car.class), eq(GetCarDTO.class))).thenReturn(mock(GetCarDTO.class));
		List<GetCarDTO> users = carService.getAllByUser(anyLong());
		assertFalse(users.isEmpty());
	}

	@Nested
	class CreateUserTest {
		
		@Test
		void shouldAddCarToUser() {
			
			when(userRepository.findById(anyLong())).thenReturn(Optional.of(UserMockTest.userA));
			when(carRepository.existsByLicensePlate(anyString())).thenReturn(Boolean.FALSE);
			when(carRepository.save(any(Car.class))).thenReturn(CarMockTest.carA);
			when(modelMapper.map(any(CreateCarDTO.class), eq(Car.class))).thenReturn(mock(Car.class));
			when(modelMapper.map(any(Car.class), eq(CreateCarDTO.class))).thenReturn(CarMockTest.createdCarA);
			
			CreateCarDTO car = carService.addCarToUser(UserMockTest.userA.getId(), CarMockTest.createdCarA);
			
			assertNotNull(car);
			assertEquals(CarMockTest.createdCarA.getId(), car.getId());
		}
		
		@Test
		void shouldNotAddCarWithExistingLicensePlate() {
			when(userRepository.findById(anyLong())).thenReturn(Optional.of(UserMockTest.userA));
			when(carRepository.existsByLicensePlate(anyString())).thenReturn(Boolean.TRUE);
			assertThrows(CustomBadRequestException.class,
					() -> carService.addCarToUser(UserMockTest.userA.getId(), CarMockTest.createdCarA),
					"License plate already exists");
		}
		
		@Test
		void shouldNotCreateUserWithExistingLogin() {
			when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
			assertThrows(ResourceNotFoundException.class,
					() -> carService.addCarToUser(UserMockTest.userA.getId(), CarMockTest.createdCarA),
					"User not found");
		}
	}
	
	@Nested
	class GetCarByIDTest {

		@Test
		void shouldGetUserByIDAndUserID() {
			when(carRepository.findByIdAndUserId(anyLong(), anyLong())).thenReturn(Optional.of(CarMockTest.carA));
			when(modelMapper.map(any(Car.class), eq(GetCarDTO.class))).thenReturn(CarMockTest.getCarA);
			
			GetCarDTO car = carService.getCarByIDAndUserByID(eq(CarMockTest.carA.getId()), anyLong());
			
			assertNotNull(car);
			assertEquals(CarMockTest.carA.getId(), car.getId());
		}
		
		@Test
		void shouldNotGetUserByID() {
			when(carRepository.findByIdAndUserId(anyLong(), anyLong())).thenReturn(Optional.empty());
			assertNull(carService.getCarByIDAndUserByID(anyLong(), anyLong()));
		}
	}
	
	@Nested
	class DeleteCarTest {

		@Test
		void shouldDeleteUserByID() {
			when(carRepository.existsByIdAndUserId(anyLong(), anyLong())).thenReturn(Boolean.TRUE);
			doNothing().when(carRepository).deleteById(anyLong());
			assertDoesNotThrow(() -> carService.deleteCarByIDAndUserID(eq(CarMockTest.carA.getId()), anyLong()));
		}
		
		@Test
		void shouldNotDeleteUserByUnexistingID() {
			when(carRepository.existsByIdAndUserId(anyLong(), anyLong())).thenReturn(Boolean.FALSE);
			assertThrows(ResourceNotFoundException.class, () -> carService.deleteCarByIDAndUserID(eq(CarMockTest.carA.getId()), anyLong()));
		}
	}
	
	@Nested
	class UpdateCarTest {
		
		@Test
		void shouldUpdateCar() {
			when(carRepository.findByIdAndUserId(anyLong(), anyLong())).thenReturn(Optional.of(CarMockTest.carA));
			when(carRepository.existsByLicensePlate(anyString())).thenReturn(Boolean.FALSE);
			when(carRepository.save(any(Car.class))).thenReturn(CarMockTest.carA);
			when(modelMapper.map(any(Car.class), eq(UpdateCarDTO.class))).thenReturn(CarMockTest.updatedCarA);
			
			UpdateCarDTO car = carService.updateCarByUser(CarMockTest.updatedCarA.getId(), CarMockTest.carA.getUser().getId(), CarMockTest.updatedCarA);
			
			assertNotNull(car);
			assertEquals(CarMockTest.updatedCarA.getId(), car.getId());
			assertEquals(CarMockTest.updatedCarA.getColor(), car.getColor());
		}
	}
	
}
