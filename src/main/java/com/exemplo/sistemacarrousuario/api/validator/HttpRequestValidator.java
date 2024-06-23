package com.exemplo.sistemacarrousuario.api.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.stereotype.Component;

import com.exemplo.sistemacarrousuario.api.controller.exception.CustomBadRequestException;

@Component
public class HttpRequestValidator<T> {
	
	private ValidatorFactory factory;
	
	public HttpRequestValidator() {
		this.factory = Validation.buildDefaultValidatorFactory();
	}

	private Validator getValidator() {
		return factory.getValidator();
	}
	
	public void validate(T body) {
		Set<ConstraintViolation<T>> validation = getValidator().validate(body);
		
		if (!validation.isEmpty()) {
			throw new CustomBadRequestException(validation.stream().findFirst().get().getMessage());
		}
		
	}
	
}
