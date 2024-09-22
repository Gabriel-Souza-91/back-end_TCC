package com.dog.api_rest.dog;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroDog(
        @NotBlank
        String nome,

        @NotNull
        Raca raca,

        @NotNull
        Porte porte,

        @NotNull
        Double peso,

        @NotNull
        Integer idade) {
}
