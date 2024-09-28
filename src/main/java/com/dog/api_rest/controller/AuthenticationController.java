package com.dog.api_rest.controller;

import static org.springframework.http.ResponseEntity.ok;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dog.api_rest.config.bussines.BusinessException;
import com.dog.api_rest.dto.LoginResponse;
import com.dog.api_rest.dto.LoginUserDto;
import com.dog.api_rest.dto.UserDTO;
import com.dog.api_rest.model.User;
import com.dog.api_rest.service.AuthenticationService;
import com.dog.api_rest.service.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final JwtService jwtService;
    
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/novo")
    public ResponseEntity<User> register(@RequestBody UserDTO registerUserDto) {
        User registeredUser = authenticationService.criarUsuario(registerUserDto);
        return ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.login(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}