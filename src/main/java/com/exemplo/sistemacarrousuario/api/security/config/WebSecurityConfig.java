package com.exemplo.sistemacarrousuario.api.security.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.exemplo.sistemacarrousuario.api.controller.constants.ApiPathConstants;
import com.exemplo.sistemacarrousuario.api.security.JwtAuthenticationEntryPoint;
import com.exemplo.sistemacarrousuario.api.security.filters.JwtAuthenticationTokenFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
	
	@Value("${cors.allowed.origins}")
	private String corsAllowedOrigins;
	
	@Value("${cors.allowed.methods}")
	private String corsAllowedMethods;
	
	@Value("${cors.allowed.headers}")
	private String corsAllowedHeaders;
	
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
	        .cors(cors -> cors.configurationSource(getCorsConfiguration()))
	        .authorizeHttpRequests(authorizeHttpRequest());
	        
        return http.build();
    }

	private CorsConfigurationSource getCorsConfiguration() {
		
		return request -> {
		    CorsConfiguration configuration = new CorsConfiguration();
		    configuration.setAllowedOrigins(Arrays.asList(corsAllowedOrigins.split(",")));
		    configuration.setAllowedMethods(Arrays.asList(corsAllowedMethods.split(",")));
		    configuration.setAllowedHeaders(Arrays.asList(corsAllowedHeaders.split(",")));
		    return configuration;
		};
	}

	private Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> authorizeHttpRequest() {
		return authorizationManagerRequestMatcherRegistry ->
                  authorizationManagerRequestMatcherRegistry
                          .requestMatchers(ApiPathConstants.unauthenticatedApiResources).permitAll()
                          .requestMatchers(ApiPathConstants.unauthenticatedApiFilesPattern).permitAll()
                          .anyRequest().authenticated();
	}

}
