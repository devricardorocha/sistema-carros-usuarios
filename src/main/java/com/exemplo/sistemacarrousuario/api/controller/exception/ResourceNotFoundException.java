package com.exemplo.sistemacarrousuario.api.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends CustomHttpException {
    public ResourceNotFoundException(final String message) {
        super(message, HttpStatus.NOT_FOUND.value());
    }
}
