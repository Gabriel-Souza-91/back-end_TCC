package com.dog.api_rest.service;

import static java.util.Objects.nonNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dog.api_rest.config.bussines.BusinessException;
import com.dog.api_rest.dto.EnderecoDTO;
import com.dog.api_rest.dto.LoginUserDto;
import com.dog.api_rest.dto.UserDTO;
import com.dog.api_rest.model.Endereco;
import com.dog.api_rest.model.User;
import com.dog.api_rest.repository.UserRepository;

@Service
public class AuthenticationService {
	
	@Autowired
	private UserService userService;
	
	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final AuthenticationManager authenticationManager;

	public AuthenticationService(UserRepository userRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User criarUsuario(UserDTO input) {
		User user = new User();
		UserDetails loadUserByUsername = userService.loadUserByUsername(input.email());
		if(nonNull(loadUserByUsername)) {
			throw new BusinessException("Usuario ja está cadastrado");
		}
		setFieldsUser(input, user);
		setFieldsEndereco(input.endereco(), user);
		return userRepository.save(user);
	}

	public User login(LoginUserDto input) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword()));
		User findByEmail = userRepository.findByEmail(input.getEmail());
		if(nonNull(findByEmail)) {
			return userRepository.findByEmail(input.getEmail());
		}
		throw new BusinessException("Usuario ou senha Invalido.");
	}

	private void setFieldsEndereco(EnderecoDTO input, User user) {
		Endereco endereco = new Endereco();
		endereco.setCep(input.cep());
		endereco.setLogradouro(input.logradouro());
		endereco.setBairro(input.bairro());
		endereco.setCidade(input.cidade());
		endereco.setUf(input.uf());
		endereco.setNumero(input.numero());
		endereco.setComplemento(input.complemento());
		endereco.setUser(user);
		user.setEndereco(endereco);
	}

	private void setFieldsUser(UserDTO input, User user) {
		user.setNome(input.nome());
		user.setEmail(input.email());
		user.setCpf(input.cpf());
		user.setDataNascimento(input.dataNascimento());
		user.setSenha(passwordEncoder.encode(input.senha()));
	}
}