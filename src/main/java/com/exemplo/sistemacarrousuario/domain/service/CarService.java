package com.exemplo.sistemacarrousuario.domain.service;

import java.util.List;

import com.exemplo.sistemacarrousuario.domain.dto.CreateCarDTO;
import com.exemplo.sistemacarrousuario.domain.dto.GetCarDTO;

/**
 * Serviço para gerenciamento de carros do usuário.
 */
public interface CarService {

    /**
     * Adiciona carro para o usuário.
     *
     * @param user ID do usuário.
     * @param user O DTO contendo as informações para adicionar um novo carro.
     * @return o DTO do carro criado.
     */
	CreateCarDTO addCarToUser(Long userID, CreateCarDTO car);
	
    /**
     * Listar todos os carros do usuário
     *
     * @param userID ID do usuário.
     * @return a lista de DTO dos carros do usuário.
     */
	List<GetCarDTO> getAllByUser(Long userID);
}
