package com.exemplo.sistemacarrousuario.api.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorDetails resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        return new ErrorDetails(ex.getMessage(), ex.getErrorCode());
    }

    @ExceptionHandler(value = {UnprocessableEntityException.class})
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorDetails unprocessedEntityException(UnprocessableEntityException ex, WebRequest request) {
        return new ErrorDetails(ex.getMessage(), ex.getErrorCode());
    }

    @ExceptionHandler(value = {CustomHttpException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDetails badRequestException(CustomHttpException ex, WebRequest mockWebRequest) {
        return new ErrorDetails(ex.getMessage(), ex.getErrorCode());
    }
}
