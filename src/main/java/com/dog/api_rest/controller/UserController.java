package com.dog.api_rest.controller;

import com.dog.api_rest.user.DadosCadastroUser;
import com.dog.api_rest.user.User;
import com.dog.api_rest.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping("/create/user")
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroUser dados){
        repository.save(new User(dados));
    }

}
