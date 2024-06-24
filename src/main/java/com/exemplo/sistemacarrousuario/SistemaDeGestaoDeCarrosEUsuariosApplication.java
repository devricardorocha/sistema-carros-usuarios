package com.exemplo.sistemacarrousuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
@RestController
@SpringBootApplication
public class SistemaDeGestaoDeCarrosEUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaDeGestaoDeCarrosEUsuariosApplication.class, args);
	}
	
	@GetMapping("/hello")
	private ResponseEntity<String> sayHello() {
		return new ResponseEntity<String>("Hello!", HttpStatus.OK);
	}
	
}
