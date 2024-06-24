package com.exemplo.sistemacarrousuario.domain.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemplo.sistemacarrousuario.api.controller.exception.CustomBadRequestException;
import com.exemplo.sistemacarrousuario.api.controller.exception.ResourceNotFoundException;
import com.exemplo.sistemacarrousuario.domain.dto.CreateCarDTO;
import com.exemplo.sistemacarrousuario.domain.entity.Car;
import com.exemplo.sistemacarrousuario.domain.entity.User;
import com.exemplo.sistemacarrousuario.domain.repository.CarRepository;
import com.exemplo.sistemacarrousuario.domain.repository.UserRepository;
import com.exemplo.sistemacarrousuario.domain.service.CarService;

@Service
public class CarServiceImpl implements CarService {
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CreateCarDTO addCarToUser(Long userID, CreateCarDTO car) {

	    Optional<User> user = userRepository.findById(userID);
	    if (user.isEmpty())
	    	throw new ResourceNotFoundException("User not found");
	    
		if (carRepository.existsByLicensePlate(car.getLicensePlate()))
			throw new CustomBadRequestException("License plate already exists");

		Car carEntity = modelMapper.map(car, Car.class);
		carEntity.setUser(user.get());
		
		return modelMapper.map(carRepository.save(carEntity), CreateCarDTO.class);
	}

}
