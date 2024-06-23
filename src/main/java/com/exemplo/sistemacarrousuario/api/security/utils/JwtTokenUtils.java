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
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtils {

	public static String CLAIM_KEY_USERNAME = "sub";
	public static String CLAIM_KEY_ROLE = "role";
	public static String CLAIM_KEY_CREATED = "created";

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	public String getLoginFromToken(String token) {
		Optional<Claims> claims = getClaimsFromToken(token);
		return claims.isPresent() ? claims.get().getSubject() : null;
	}

	public Date getExpirationDateFromToken(String token) {
		Optional<Claims> claims = getClaimsFromToken(token);
		return claims.isPresent() ? claims.get().getExpiration() : null;
	}

	public String refreshToken(String token) {
		Optional<Claims> claims = getClaimsFromToken(token);
		if (claims.isPresent()) {
			claims.get().put(CLAIM_KEY_CREATED, new Date());
			return buildToken(claims.get());
		}

		return null;
	}

	public Boolean isTokenValid(String token) {
		return !isTokenExpired(token);
	}

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
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
