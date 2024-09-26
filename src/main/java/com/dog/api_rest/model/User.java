package com.dog.api_rest.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dog.api_rest.dto.UserDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Email(message = "Email invalido")
    @NotBlank(message = "O usuario deve informar email.")
    private String email;
    
    @NotBlank(message = "O usuario deve informar senha.")
    private String senha;

    @NotBlank(message = "O usuario deve informar nome.")
    private String nome;
    
    @NotBlank(message = "O usuario deve informar cpf.")
    private String cpf;
    
    @NotBlank(message = "O usuario deve informar data de nascimento.")
    private LocalDate dataNascimento;
    
    @Valid
    @OneToOne
    @NotNull(message = "O usuario deve informar endereco.")
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;
    
    @OneToMany(mappedBy="user")
    private Set<Dog> dogs;

    public User(UserDTO dados){
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
        this.cpf = dados.cpf();
        //this.data_nascimento = dados.data_nascimento();
        this.endereco = new Endereco(dados.endereco());
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<>();
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}


}
