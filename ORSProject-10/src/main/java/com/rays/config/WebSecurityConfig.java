package com.rays.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security configuration for the application.
 * <p>
 * Disables CSRF, enforces stateless session management, permits public access to
 * {@code /Auth/**}, {@code /User/profilePic/**}, and {@code /Jasper/report/**},
 * and registers {@link JWTRequestFilter} before the default username/password filter.
 * </p>
 *
 * @author Ajay Pratap Kerketta
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/** The JWT filter applied before Spring's default authentication filter. */
	@Autowired
	private JWTRequestFilter jwtRequestFilter;

	/**
	 * Configures HTTP security rules, session policy, and the JWT filter chain.
	 *
	 * @param http the {@link HttpSecurity} builder provided by Spring Security
	 * @throws Exception if any security configuration step fails
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/Auth/**", "/User/profilePic/**", "/Jasper/report/**").permitAll()
				.anyRequest().authenticated().and()
				.exceptionHandling()
		        .authenticationEntryPoint((request, response, authException) -> {
		            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		            response.getWriter().write("Token does not exist....Access Denied !!!");
		        })
				
				.and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		http.cors();
	}
}