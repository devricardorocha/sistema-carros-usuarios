package com.exemplo.sistemacarrousuario.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(length = 255, nullable = false)
	private String firstName;

	@Column(length = 255, nullable = false)
	private String lastName;

	@Column(length = 255, nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private LocalDate birthday;

	@Column(length = 255, nullable = false, unique = true)
	private String login;

	@Column(length = 255, nullable = false)
	private String password;

	@Column(length = 20, nullable = false)
	private String phone;

	@Column(nullable = false)
	private LocalDate creation;

	@Column
	private LocalDateTime lastLogin;

	@OneToMany
	private List<Car> cars;
}
