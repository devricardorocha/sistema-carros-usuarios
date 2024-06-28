package com.exemplo.sistemacarrousuario.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade que representa um carro.
 *
 * <p>Esta classe mapeia a tabela "cars" no banco de dados e contém informações sobre o carro, como ano, placa, modelo, cor e o usuário associado.</p>
 */
@Entity
@Table(name = "cars")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Car {

    /**
     * O ID do carro.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    /**
     * O ano do carro.
     */
	@Column(nullable = false, name = "_year")
	private Integer year;

    /**
     * A placa do carro.
     */
	@Column(length = 20, nullable = false, unique = true)
	private String licensePlate;

    /**
     * O modelo do carro.
     */
	@Column(length = 255, nullable = false)
	private String model;

    /**
     * A cor do carro.
     */
	@Column(length = 255, nullable = false)
	private String color;

    /**
     * O usuário associado ao carro.
     */
	@ManyToOne(optional = false)
	@JoinColumn(name = "users_id")
	private User user;

}
