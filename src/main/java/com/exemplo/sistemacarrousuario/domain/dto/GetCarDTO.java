package com.exemplo.sistemacarrousuario.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO para retornar um carro.
 *
 * <p>Esta classe é usada para transferir os dados necessários para retornar um carro.</p>
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GetCarDTO {

    /**
     * O ID do carro.
     */
    @Schema(description = "ID do carro", example = "1")
    private Long id;

    /**
     * O ano de fabricação do carro.
     */
	@Schema(description = "Ano de fabricação", example = "2018")
	private Integer year;
	
    /**
     * A placa do carro.
     */
	@Schema(description = "Placa do carro", example = "PDV-0625")
	private String licensePlate;
	
    /**
     * O modelo do carro.
     */
	@Schema(description = "Modelo do carro", example = "Audi")
	private String model;
	
    /**
     * A cor do carro.
     */
	@Schema(description = "Cor do carro", example = "White")
	private String color;

}
