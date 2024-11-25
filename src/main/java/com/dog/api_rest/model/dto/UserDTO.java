package com.dog.api_rest.model.dto;


import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserDTO(
        @NotBlank
        String nome,

        @NotBlank
        @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")
        String cpf,

        @NotBlank
        @Email
        String email,
        
        @NotBlank
        String senha,
        
        @NotNull(message = "A data de nascimento não pode ser nula ou vazia")
        @DateTimeFormat(fallbackPatterns = "yyyy-MM-dd")
        LocalDate dataNascimento
		){
}
