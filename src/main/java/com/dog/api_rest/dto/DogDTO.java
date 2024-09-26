package com.dog.api_rest.dto;

import com.dog.api_rest.model.enums.Porte;
import com.dog.api_rest.model.enums.Raca;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DogDTO(
		@NotBlank String nome,
		@NotNull Raca raca,
		@NotNull Porte porte,
		@NotNull Double peso,
		@NotNull Integer idade) {
}
