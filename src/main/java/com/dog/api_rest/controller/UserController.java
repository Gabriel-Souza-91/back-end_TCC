package com.dog.api_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dog.api_rest.dto.UserDTO;
import com.dog.api_rest.model.User;
import com.dog.api_rest.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;

    @PostMapping
    public ResponseEntity<User> novoUsuario(@RequestBody @Valid UserDTO dados){
    	User novoUsuario = userService.novoUsuario(dados);
    	return ResponseEntity.ok(novoUsuario);
    }
}
