package com.exemplo.sistemacarrousuario.domain.service;

import java.util.List;

import com.exemplo.sistemacarrousuario.domain.dto.CreateCarDTO;
import com.exemplo.sistemacarrousuario.domain.dto.GetCarDTO;
import com.exemplo.sistemacarrousuario.domain.dto.UpdateCarDTO;

/**
 * Serviço para gerenciamento de carros do usuário.
 */
public interface CarService {

    /**
     * Adiciona carro para o usuário.
     *
     * @param userID ID do usuário.
     * @param car O DTO contendo as informações para adicionar um novo carro.
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
	
    /**
     * Obtém um carro específico por ID do carro e ID de usuário.
     *
     * @param id o ID do carro
     * @param userId o ID do usuário
     * @return o DTO contendo as informações do carro
     */
    GetCarDTO getCarByIDAndUserByID(Long id, Long userId);
    
	
    /**
     * Remove um carro do usuário pelo ID.
     *
     * <p>Este método deleta um carro do usuário existente com base no ID do carro e do usuário.</p>
     *
     * @param id o ID do carro a ser removido
     * @param userId o ID do usuário
     */
    void deleteCarByIDAndUserID(Long id, Long userId);
    
    /**
     * Atualiza as informações de um usuário existente.
     *
     * @param id o ID do carro a ser atualizado
     * @param userId o ID do usuário do carro a ser atualizado
     * @param car O DTO contendo as novas informações do carro a serem atualizadas.
     * @return O DTO atualizado do carro.
     */
    UpdateCarDTO updateCarByUser(Long id, Long userId, UpdateCarDTO car);
    
}
