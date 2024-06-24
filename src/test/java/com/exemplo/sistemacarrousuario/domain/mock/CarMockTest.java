package com.exemplo.sistemacarrousuario.domain.mock;

import com.exemplo.sistemacarrousuario.domain.dto.CreateCarDTO;
import com.exemplo.sistemacarrousuario.domain.dto.GetCarDTO;
import com.exemplo.sistemacarrousuario.domain.entity.Car;

public abstract class CarMockTest {

	public static Car carA = Car.builder().id(999l).color("Preto").model("Corolla").licensePlate("ABC-1234").year(2020)
			.user(UserMockTest.userA).build();

	public static Car carB = Car.builder().id(999l).color("Branco").model("Civic").licensePlate("DEF-5678").year(2019)
			.user(UserMockTest.userA).build();

	public static CreateCarDTO createdCarA = CreateCarDTO.builder().id(999l).color("Branco").model("Civic")
			.licensePlate("DEF-5678").year(2019).build();
	
	public static GetCarDTO getCarA = GetCarDTO.builder().id(999l).color("Branco").model("Civic")
			.licensePlate("DEF-5678").year(2019).build();
}
