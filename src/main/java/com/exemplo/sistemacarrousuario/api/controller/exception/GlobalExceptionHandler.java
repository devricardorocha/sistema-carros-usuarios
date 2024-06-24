package com.exemplo.sistemacarrousuario.api.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
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

    @ExceptionHandler(value = {AuthenticationFailedException.class})
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ErrorDetails unauthorizedException(AuthenticationFailedException ex, WebRequest mockWebRequest) {
        return new ErrorDetails(ex.getMessage(), ex.getErrorCode());
    }

    @ExceptionHandler(value = {InternalAuthenticationServiceException.class})
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ErrorDetails unauthorizedException(InternalAuthenticationServiceException ex, WebRequest mockWebRequest) {
        return new ErrorDetails(ex.getMessage(), HttpStatus.UNAUTHORIZED.value());
    }
    
    @ExceptionHandler(value = {BadCredentialsException.class})
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ErrorDetails unauthorizedException(BadCredentialsException ex, WebRequest mockWebRequest) {
        return new ErrorDetails("Invalid login or password", HttpStatus.UNAUTHORIZED.value());
    }
    
    @ExceptionHandler(value = {CustomHttpException.class, HttpMediaTypeNotSupportedException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDetails badRequestException(CustomHttpException ex, WebRequest mockWebRequest) {
        return new ErrorDetails(ex.getMessage(), ex.getErrorCode());
    }
    
    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDetails internalServerErrorException(Exception ex, WebRequest mockWebRequest) {
        return new ErrorDetails("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
    
    
}
