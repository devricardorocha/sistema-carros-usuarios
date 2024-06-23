package com.exemplo.sistemacarrousuario.api.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomBadRequestException extends CustomHttpException {
    public CustomBadRequestException(final String message) {
        super(message, HttpStatus.BAD_REQUEST.value());
    }
}
