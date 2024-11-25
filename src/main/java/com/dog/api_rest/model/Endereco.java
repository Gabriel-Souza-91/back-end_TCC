package com.dog.api_rest.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "enderecos")
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O usuario deve informar cep.")
	private String cep;

	@NotBlank(message = "O usuario deve informar logradouro.")
	private String logradouro;

	@NotBlank(message = "O usuario deve informar bairro.")
	private String bairro;

	@NotBlank(message = "O usuario deve informar cidade.")
	private String cidade;

	@NotBlank(message = "O usuario deve informar uf.")
	private String uf;

	@NotBlank(message = "O usuario deve informar numero.")
	private String numero;

	@NotBlank(message = "O usuario deve informar complemento.")
	private String complemento;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toStringExclude(this, "user");
	}
}
