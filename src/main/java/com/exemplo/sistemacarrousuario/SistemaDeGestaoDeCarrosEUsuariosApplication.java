package com.exemplo.sistemacarrousuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.v3.oas.annotations.Hidden;

/**
 * Sistema de Gestão de Carros e Usuários.
 *
 * <p>Este sistema permite a gestão de carros e usuários, fornecendo funcionalidades
 * como criação, leitura, atualização e exclusão de registros e a autenticação dos recursos da API Rest. O sistema é composto
 * por um backend em Spring Boot e um frontend em Angular.</p>
 *
 * <p>Informações sobre o Desenvolvedor:</p>
 * <ul>
 *     <li>Nome: Ricardo Rocha</li>
 *     <li><a href="https://www.linkedin.com/in/ricardo-de-lima-rocha/">LinkedIn</a></li>
 *     <li><a href="https://github.com/devricardorocha">GitHub</a></li>
 * </ul>
 *
 * <p>Repositório do Projeto no GitHub:</p>
 * <ul>
 *     <li>GitHub: <a href="https://github.com/devricardorocha/sistema-carros-usuarios">https://github.com/devricardorocha/sistema-carros-usuarios</a></li>
 * </ul>
 *
 *
 * @version 1.0
 * @since 2024-06-28
 */

@Hidden
@Controller
@SpringBootApplication
public class SistemaDeGestaoDeCarrosEUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaDeGestaoDeCarrosEUsuariosApplication.class, args);
	}
	
	@GetMapping("/hello")
	private ResponseEntity<String> sayHello() {
		return new ResponseEntity<String>("Hello!", HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	private String home() {
		return "redirect:/docs/index.html";
	}
	
}
