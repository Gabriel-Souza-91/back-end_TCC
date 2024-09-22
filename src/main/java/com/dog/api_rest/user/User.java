package com.dog.api_rest.user;

import com.dog.api_rest.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;
    private String cpf;
   // private Date data_nascimento;

    @Embedded
    private Endereco endereco;

    public User(DadosCadastroUser dados){
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
        this.cpf = dados.cpf();
        //this.data_nascimento = dados.data_nascimento();
        this.endereco = new Endereco(dados.endereco());
    }


}
