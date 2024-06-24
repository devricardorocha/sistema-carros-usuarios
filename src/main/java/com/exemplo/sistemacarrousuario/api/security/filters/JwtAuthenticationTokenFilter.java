package com.exemplo.sistemacarrousuario.api.security.filters;

import java.io.IOException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.exemplo.sistemacarrousuario.api.controller.exception.ResourceNotFoundException;
import com.exemplo.sistemacarrousuario.api.security.utils.JwtTokenUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	private static final String AUTH_HEADER = "Authorization";
	private static final String BEARER_PREFIX = "Bearer";

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtils jwtTokenUtils;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authHeader = request.getHeader(AUTH_HEADER);

		String token = null;
		String login = null;
		if (Objects.nonNull(authHeader) && authHeader.startsWith(BEARER_PREFIX)) {
			token = authHeader.substring(7);
			login = jwtTokenUtils.getLoginFromToken(token);
			
			if (Objects.nonNull(login) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
				try {
					UserDetails details = userDetailsService.loadUserByUsername(login);

					if (jwtTokenUtils.isTokenValid(token)) {
						UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(details,
								null, details.getAuthorities());
						authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

						SecurityContextHolder.getContext().setAuthentication(authentication);
					}
				} catch (ResourceNotFoundException e) {
					throw new ResourceNotFoundException("Invalid login or password");
				}
			}
			
		}

		filterChain.doFilter(request, response);
	}

}
