package com.dog.api_rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.dog.api_rest.config.bussines.GlobalExceptionHandler;


@SpringBootApplication
public class ApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestApplication.class, args);
	}

	@Bean(name = "globalExceptionHandler")
	GlobalExceptionHandler globalExceptionHandler() {
		return new GlobalExceptionHandler();
	}

	@Bean(name = "businessExceptionHandler")
	GlobalExceptionHandler businessExceptionHandler() {
		return new GlobalExceptionHandler();
	}
}
