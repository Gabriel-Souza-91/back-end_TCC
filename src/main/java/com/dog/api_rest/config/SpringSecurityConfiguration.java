package com.dog.api_rest.config;

import static org.springframework.http.HttpMethod.POST;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
	
	@SuppressWarnings("deprecation")
	@Bean 
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(requests -> requests
                .requestMatchers(POST, "/users").permitAll()
                .anyRequest().authenticated()).formLogin(login -> login
                .loginPage("/login").permitAll());
	    return http.build();
	}
}


