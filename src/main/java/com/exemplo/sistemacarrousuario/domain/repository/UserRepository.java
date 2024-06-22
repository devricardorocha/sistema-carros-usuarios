package com.exemplo.sistemacarrousuario.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exemplo.sistemacarrousuario.domain.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
