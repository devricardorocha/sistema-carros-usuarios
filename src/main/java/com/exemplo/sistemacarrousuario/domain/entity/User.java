package com.exemplo.sistemacarrousuario.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User implements UserDetails {

	private static final long serialVersionUID = -7599246158852875635L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 255, nullable = false)
	private String firstName;

	@Column(length = 255, nullable = false)
	private String lastName;

	@Column(length = 255, nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private LocalDate birthday;

	@Column(length = 255, nullable = false, unique = true)
	private String login;

	@Column(length = 255, nullable = false)
	private String password;

	@Column(length = 20, nullable = false)
	private String phone;

	@Column(nullable = false)
	private LocalDate created;

	@Column
	private LocalDateTime lastLogin;

	@OneToMany
	private List<Car> cars;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return Boolean.TRUE;
	}

	@Override
	public boolean isAccountNonLocked() {
		return Boolean.TRUE;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return Boolean.TRUE;
	}

	@Override
	public boolean isEnabled() {
		return Boolean.TRUE;
	}
}
