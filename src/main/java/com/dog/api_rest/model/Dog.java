package com.dog.api_rest.model;

import com.dog.api_rest.model.enums.Porte;
import com.dog.api_rest.model.enums.Raca;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity(name = "dog")
@Table(name = "dogs")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "O nome do cachorro não pode ser vazio ou nulo")
    private String name;
    
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O nome do cachorro não pode ser nulo")
    private Raca raca;

    @NotNull(message = "O nome do cachorro não pode ser nulo")
    @Enumerated(EnumType.STRING)
    private Porte porte;

    @NotNull(message = "O nome do cachorro não pode ser nulo")
    private Double peso;
    
    @NotNull(message = "O nome do cachorro não pode ser nulo")
    private Integer idade;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
}
