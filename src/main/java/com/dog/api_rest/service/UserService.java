package com.dog.api_rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dog.api_rest.dto.UserDTO;
import com.dog.api_rest.model.User;
import com.dog.api_rest.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		return (UserDetails) user;
	}

	public User novoUsuario(@Valid UserDTO dadosDTO) {
		User novoUsuario = new User(dadosDTO);
		novoUsuario.setSenha(passwordEncoder.encode(dadosDTO.senha()));
		User userDB = userRepository.save(novoUsuario);
		userDB.setSenha(null);
		return userDB;
	}

}
