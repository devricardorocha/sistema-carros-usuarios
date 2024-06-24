package com.exemplo.sistemacarrousuario.domain.service;

import com.exemplo.sistemacarrousuario.domain.dto.CreateCarDTO;

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
	
}
