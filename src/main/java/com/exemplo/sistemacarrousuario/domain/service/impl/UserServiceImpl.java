package com.exemplo.sistemacarrousuario.domain.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exemplo.sistemacarrousuario.api.controller.exception.CustomBadRequestException;
import com.exemplo.sistemacarrousuario.api.controller.exception.ResourceNotFoundException;
import com.exemplo.sistemacarrousuario.domain.dto.CreateUserDTO;
import com.exemplo.sistemacarrousuario.domain.dto.GetUserDTO;
import com.exemplo.sistemacarrousuario.domain.entity.User;
import com.exemplo.sistemacarrousuario.domain.repository.UserRepository;
import com.exemplo.sistemacarrousuario.domain.service.UserService;
import com.exemplo.sistemacarrousuario.domain.utils.PasswordUtils;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<GetUserDTO> getAll() {
		return userRepository.findAll().stream().map(user -> modelMapper.map(user, GetUserDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public CreateUserDTO createUser(CreateUserDTO user) {
		if (userRepository.existsByEmail(user.getEmail()))
			throw new CustomBadRequestException("Email already exists");

		if (userRepository.existsByLogin(user.getLogin()))
			throw new CustomBadRequestException("Login already exists");
		
		User entity = modelMapper.map(user, User.class);
		entity.setCreated(LocalDate.now());
		entity.setPassword(PasswordUtils.encode(user.getPassword()));
		return modelMapper.map(userRepository.save(entity), CreateUserDTO.class);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByLogin(username);
		if (Objects.isNull(user))
			throw new ResourceNotFoundException("User not found");

		return user;
	}

	@Override
	public GetUserDTO getUserByID(Integer id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent())
			return modelMapper.map(userOptional.get(), GetUserDTO.class);
		
		return null;
	}

}
