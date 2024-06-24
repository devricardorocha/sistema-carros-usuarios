package com.exemplo.sistemacarrousuario.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exemplo.sistemacarrousuario.domain.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

	Boolean existsByLicensePlate(String email);

	List<Car> findByUserId(Long userId);

	Optional<Car> findByIdAndUserId(Long id, Long userId);

}
