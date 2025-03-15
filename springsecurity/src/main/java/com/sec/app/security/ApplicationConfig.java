package com.sec.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class ApplicationConfig {
		
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public SecurityFilterChain httpSecurity(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf(csrf->csrf.disable()).authorizeHttpRequests(auth->auth.
				requestMatchers("/login").permitAll()
				.requestMatchers("/getuser").hasAnyRole("ADMIN","USER")
				.requestMatchers("/saveuser").hasRole("ADMIN")
				.requestMatchers("/updateuser").hasRole("USER")
				.requestMatchers("/deleteuser/**").hasRole("ADMIN"))
				.httpBasic(Customizer.withDefaults())
				.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return httpSecurity.build();
	}

}

//csrf->is helping to user http url's insted of https
//authorizeHttpRequests->it is used to give authorization on api's'
//requestMatchers-> here i should have to menction api and it access
//httpbasic-> after prepared give access to all http url access
//sessionManagment-> it will help in mantain after login
