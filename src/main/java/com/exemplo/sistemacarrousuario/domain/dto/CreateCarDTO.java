package com.exemplo.sistemacarrousuario.domain.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO para criação de um carro.
 *
 * <p>Esta classe é usada para transferir os dados necessários para criar um novo carro.</p>
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateCarDTO {

    /**
     * O ID do carro.
     */
    @Schema(description = "ID do carro", example = "1")
    private Long id;

    /**
     * O ano de fabricação do carro.
     */
	@NotNull
	@Schema(description = "Ano de fabricação", example = "2018")
	private Integer year;
	
    /**
     * A placa do carro.
     */
	@NotEmpty
	@Length(min=7, max=8)
	@Schema(description = "Placa do carro", example = "PDV-0625")
	private String licensePlate;
	
    /**
     * O modelo do carro.
     */
	@NotEmpty
	@Schema(description = "Modelo do carro", example = "Audi")
	private String model;
	
    /**
     * A cor do carro.
     */
	@NotEmpty
	@Schema(description = "Cor do carro", example = "White")
	private String color;

}
