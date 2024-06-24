package com.exemplo.sistemacarrousuario.api.security.utils;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * Utilitário para operações relacionadas ao token JWT.
 *
 * <p>Esta classe fornece métodos para gerar, validar, atualizar e extrair informações de tokens JWT.</p>
 */
@Component
public class JwtTokenUtils {

	public static String CLAIM_KEY_USERNAME = "sub";
	public static String CLAIM_KEY_ROLE = "role";
	public static String CLAIM_KEY_CREATED = "created";

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	 /**
     * Obtém o login (username) do token JWT.
     *
     * @param token o token JWT
     * @return o login extraído do token
     */
	public String getLoginFromToken(String token) {
		Optional<Claims> claims = getClaimsFromToken(token);
		return claims.isPresent() ? claims.get().getSubject() : null;
	}

    /**
     * Obtém a data de expiração do token JWT.
     *
     * @param token o token JWT
     * @return a data de expiração do token
     */
	public Date getExpirationDateFromToken(String token) {
		Optional<Claims> claims = getClaimsFromToken(token);
		return claims.isPresent() ? claims.get().getExpiration() : null;
	}

    /**
     * Atualiza o token JWT, redefinindo a data de criação.
     *
     * @param token o token JWT
     * @return o novo token atualizado
     */
	public String refreshToken(String token) {
		Optional<Claims> claims = getClaimsFromToken(token);
		if (claims.isPresent()) {
			claims.get().put(CLAIM_KEY_CREATED, new Date());
			return buildToken(claims.get());
		}

		return null;
	}

    /**
     * Verifica se o token JWT é válido.
     *
     * @param token o token JWT
     * @return {@code true} se o token for válido, caso contrário {@code false}
     */
	public Boolean isTokenValid(String token) {
		return !isTokenExpired(token);
	}

    /**
     * Gera um novo token JWT para um usuário.
     *
     * @param details os detalhes do usuário
     * @return o token JWT gerado
     */
	public String getToken(UserDetails details) {

		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIM_KEY_USERNAME, details.getUsername());
		details.getAuthorities().forEach(auth -> claims.put(CLAIM_KEY_ROLE, auth.getAuthority()));
		claims.put(CLAIM_KEY_CREATED, new Date());
		return buildToken(claims);
	}

	private boolean isTokenExpired(String token) {

		Date expiration = getExpirationDateFromToken(token);
		if (Objects.isNull(expiration)) {
			return Boolean.FALSE;
		}

		return expiration.before(new Date());
	}

	private String buildToken(Map<String, Object> claims) {
		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(getExpirationDate())
                .setIssuedAt(new Date(System.currentTimeMillis()))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	private Date getExpirationDate() {
		return new Date(System.currentTimeMillis() + expiration * 1000);
	}

	private Optional<Claims> getClaimsFromToken(String token) {

		Optional<Claims> claims = Optional.empty();
		try {
			claims =
					Optional.ofNullable(
						Jwts
			                .parserBuilder()
			                .setSigningKey(getSignInKey())
			                .build()
			                .parseClaimsJws(token)
			                .getBody());
		} catch (Exception e) {
			claims = Optional.empty();
		}

		return claims;
	}
	
    private Key getSignInKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

}
