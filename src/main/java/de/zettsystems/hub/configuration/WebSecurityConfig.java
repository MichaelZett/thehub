package de.zettsystems.hub.configuration;

import de.zettsystems.hub.security.application.CustomUserDetailsServiceImpl;
import de.zettsystems.hub.security.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

	private final UserRepository userRepository;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable) // sonst funktioniert Swagger Post/Put/Delete nicht.... TODO
				// h2-console sonst nicht nutzbar, uninteressant weil echte DB später
				.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
				.authorizeHttpRequests(authConfig -> {
					authConfig.requestMatchers("/actuator", //
							"/swagger-ui", //
							"/v3/api-docs", //
							"/h2-console", //
							"/api/users" //
					).hasRole("ADMIN");
					authConfig.anyRequest().authenticated();
				}).formLogin(Customizer.withDefaults()).logout(logout -> {
					logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
					logout.logoutSuccessUrl("/");
					logout.deleteCookies("JSESSIONID");
					logout.invalidateHttpSession(true);
				})
				.httpBasic(Customizer.withDefaults());
		return http.build();
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	UserDetailsService userDetailsService() {
		return new CustomUserDetailsServiceImpl(userRepository);
	}

	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
}
