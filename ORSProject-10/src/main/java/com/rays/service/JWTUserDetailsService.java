package com.rays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rays.dto.UserDTO;

/**
 * Spring Security {@link UserDetailsService} implementation that loads user
 * details from the application's own {@link UserServiceInt}. Also exposes a
 * {@link BCryptPasswordEncoder} bean for password encoding.
 *
 * @author Ajay Pratap Kerketta
 */
@Service
public class JWTUserDetailsService implements UserDetailsService {

	/**
	 * Provides a {@link BCryptPasswordEncoder} bean for use across the application.
	 *
	 * @return a new {@link BCryptPasswordEncoder} instance
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	UserServiceInt userService;

	/**
	 * Loads a {@link UserDetails} object by login ID (username). Looks up the user
	 * via {@link UserServiceInt#findByLoginId} and wraps the result in a Spring
	 * Security {@link User} with role {@code USER} and BCrypt-encoded password.
	 *
	 * @param username the login ID to look up
	 * @return the {@link UserDetails} for the found user
	 * @throws UsernameNotFoundException if no user exists with the given login ID
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserDTO user = userService.findByLoginId(username, null);

		if (user == null) {
			System.out.println("user found nuulllll");
			throw new UsernameNotFoundException("User not found with username : " + username);
		}

		return User.builder().username(user.getLoginId()).password(passwordEncoder().encode(user.getPassword()))
				.roles("USER").build();
	}
}