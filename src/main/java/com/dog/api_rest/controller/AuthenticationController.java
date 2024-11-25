package com.dog.api_rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dog.api_rest.model.Endereco;
import com.dog.api_rest.model.User;
import com.dog.api_rest.model.dto.EnderecoDTO;
import com.dog.api_rest.model.dto.LoginResponseDTO;
import com.dog.api_rest.model.dto.LoginUserDto;
import com.dog.api_rest.model.dto.UserDTO;
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
    public ResponseEntity<LoginResponseDTO> register(@RequestBody UserDTO registerUserDto) {
        authenticationService.criarUsuario(registerUserDto);
        User authenticatedUser = authenticationService.login(LoginUserDto.builder().email(registerUserDto.email()).password(registerUserDto.senha()).build());
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponseDTO loginResponse = new LoginResponseDTO();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.login(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponseDTO loginResponse = new LoginResponseDTO();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
    
    @PostMapping("/endereco")
    public ResponseEntity<Endereco> authenticate(@RequestBody EnderecoDTO enderecoDTO) {
    	UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	authenticationService.criarEndereco(user, enderecoDTO);
        return ResponseEntity.noContent().build();
    }
}