package com.exemplo.sistemacarrousuario.api.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.exemplo.sistemacarrousuario.api.security.JwtAuthenticationEntryPoint;
import com.exemplo.sistemacarrousuario.api.security.filters.JwtAuthenticationTokenFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
	
    private final AuthenticationProvider authenticationProvider;
	private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public WebSecurityConfig(
    		AuthenticationProvider authenticationProvider,
    		JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter,
    		JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationTokenFilter = jwtAuthenticationTokenFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
        	.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .authenticationProvider(authenticationProvider)
	        .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
	        .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint))
	        .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
	        .authorizeHttpRequests(authorizeHttpRequest());
	        
        return http.build();
    }

	private Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> authorizeHttpRequest() {
		return authorizationManagerRequestMatcherRegistry ->
                  authorizationManagerRequestMatcherRegistry
                          .requestMatchers("/users/**", "/signin/**","/v3/api-docs/**", "/h2-console/**").permitAll()
                          .anyRequest().authenticated();
	}

}
