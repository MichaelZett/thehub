package com.buhl.hub.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable) // sonst funktioniert Swagger Post/Put/Delete nicht.... TODO
				.authorizeHttpRequests(authConfig -> {
					authConfig.requestMatchers("/actuator", //
							"/swagger-ui", //
							"/v3/api-docs", //
							"/api/users" //
					).hasRole("ADMIN");
					authConfig.requestMatchers("/konto").hasRole("USER");
					authConfig.anyRequest().authenticated();
				}).formLogin(Customizer.withDefaults()).logout(logout -> {
					logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
					logout.logoutSuccessUrl("/");
					logout.deleteCookies("JSESSIONID");
					logout.invalidateHttpSession(true);
				}).httpBasic(Customizer.withDefaults());
		return http.build();
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {

		UserDetails peter = User.builder() //
				.username("peter") //
				.password(passwordEncoder().encode("peter")) //
				.roles("USER").build();

		UserDetails admin = User.builder()//
				.username("admin") //
				.password(passwordEncoder().encode("admin"))//
				.roles("ADMIN")//
				.build();
		return new InMemoryUserDetailsManager(peter, admin);
	}

	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
}
