package com.exemplo.sistemacarrousuario.api.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class AuthenticationFailedException extends CustomHttpException {
    public AuthenticationFailedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED.value());
    }
}
