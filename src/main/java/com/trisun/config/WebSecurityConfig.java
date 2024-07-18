package com.trisun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.sessionManagement(customizer ->customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		httpSecurity.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
		httpSecurity.csrf(csrf -> csrf.disable());
		return httpSecurity.build();
	}
}
