package com.exemplo.sistemacarrousuario.api.controller.exception;

public abstract class CustomHttpException extends RuntimeException {
	private Integer errorCode;
	
	public CustomHttpException(String message, Integer errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public Integer getErrorCode() {
		return errorCode;
	}
	
}
