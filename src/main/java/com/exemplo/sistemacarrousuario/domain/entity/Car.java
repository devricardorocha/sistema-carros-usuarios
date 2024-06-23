package com.exemplo.sistemacarrousuario.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cars")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private Integer year;

	@Column(length = 20, nullable = false, unique = true)
	private String licensePlate;

	@Column(length = 255, nullable = false)
	private String model;

	@Column(length = 255, nullable = false)
	private String color;

	@ManyToOne(optional = false)
	private User user;

}
