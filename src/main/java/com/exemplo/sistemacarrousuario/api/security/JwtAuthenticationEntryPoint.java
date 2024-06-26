package com.exemplo.sistemacarrousuario.api.security;

import java.io.IOException;
import java.util.Objects;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		String authorization = request.getHeader("Authorization");
		String message = "Unauthorized";
		if (Objects.nonNull(authorization))
			message += " - invalid session";
			
		response.setContentType("application/json;charset=UTF-8");
    	response.setStatus(HttpStatus.UNAUTHORIZED.value());
    	response.getWriter().write(new JSONObject()
                .put("errorCode", HttpStatus.UNAUTHORIZED.value())
                .put("message", message)
                .toString());
	}

}
