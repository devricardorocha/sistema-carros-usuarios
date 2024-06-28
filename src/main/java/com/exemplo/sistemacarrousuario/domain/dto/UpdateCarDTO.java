package com.exemplo.sistemacarrousuario.domain.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO para atualização de um carro.
 *
 * <p>Esta classe é usada para transferir os dados necessários para atualizar um novo carro.</p>
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateCarDTO {

    /**
     * O ID do carro.
     */
	@NotNull
    @Schema(description = "ID do carro", example = "1")
    private Long id;

    /**
     * O ano de fabricação do carro.
     */
	@NotNull
	@Schema(description = "Ano de fabricação", example = "2018", minimum = "1900", maximum = "2100")
	private Integer year;
	
    /**
     * A placa do carro.
     */
	@NotEmpty
	@Length(min = 7, max = 8)
	@Schema(description = "Placa do carro", example = "PDV-0625", minLength = 7, maxLength = 8)
	private String licensePlate;
	
    /**
     * O modelo do carro.
     */
	@NotEmpty
	@Length(min = 1, max = 255)
	@Schema(description = "Modelo do carro", example = "Audi", minLength = 1, maxLength = 255)
	private String model;
	
    /**
     * A cor do carro.
     */
	@NotEmpty
	@Length(min = 1, max = 255)
	@Schema(description = "Cor do carro", example = "White", minLength = 1, maxLength = 255)
	private String color;

}
