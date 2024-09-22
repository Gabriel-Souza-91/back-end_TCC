package com.dog.api_rest.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep,

        @NotBlank
        String logradouro,

        @NotBlank
        String bairro,

        @NotBlank
        String cidade,

        @NotBlank
        String uf,

        String numero,

        String complemento){
}
