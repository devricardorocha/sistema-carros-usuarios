package com.exemplo.sistemacarrousuario.domain.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemplo.sistemacarrousuario.api.controller.exception.CustomBadRequestException;
import com.exemplo.sistemacarrousuario.api.controller.exception.ResourceNotFoundException;
import com.exemplo.sistemacarrousuario.domain.dto.CreateCarDTO;
import com.exemplo.sistemacarrousuario.domain.dto.GetCarDTO;
import com.exemplo.sistemacarrousuario.domain.dto.UpdateCarDTO;
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

	@Override
	public List<GetCarDTO> getAllByUser(Long userID) {
		return carRepository.findByUserId(userID).stream().map(car -> modelMapper.map(car, GetCarDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public GetCarDTO getCarByIDAndUserByID(Long id, Long userId) {
		Optional<Car> carOptional = carRepository.findByIdAndUserId(id, userId);
		return carOptional.map(car -> modelMapper.map(carOptional.get(), GetCarDTO.class)).orElse(null);
	}

	@Override
	public void deleteCarByIDAndUserID(Long id, Long userId) {
		if (!carRepository.existsByIdAndUserId(id, userId))
			throw new ResourceNotFoundException("Car not found");
		
		carRepository.deleteById(id);
	}

	@Override
	public UpdateCarDTO updateCarByUser(Long id, Long userId, UpdateCarDTO car) {
		
		if (!id.equals(car.getId()))
			throw new CustomBadRequestException("Invalid fields");
		
		Optional<Car> carOptional = carRepository.findByIdAndUserId(id, userId);
		if (carOptional.isEmpty())
			throw new ResourceNotFoundException("Car not found");

		if (carRepository.existsByLicensePlate(car.getLicensePlate()))
			throw new CustomBadRequestException("License plate already exists");
		
		Car updatedCar = carOptional.get();
		
		updatedCar.setLicensePlate(car.getLicensePlate());
		updatedCar.setModel(car.getModel());
		updatedCar.setColor(car.getColor());
		updatedCar.setYear(car.getYear());
		
		return modelMapper.map(carRepository.save(updatedCar), UpdateCarDTO.class);
	}
	
}
