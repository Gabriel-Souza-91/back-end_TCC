package com.dog.api_rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.dog.api_rest.model.Dog;
import com.dog.api_rest.model.User;
import com.dog.api_rest.model.dto.DogDTO;
import com.dog.api_rest.repository.DogRepository;

@Service
public class DogService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DogRepository repository;
	
	public void novoDog(DogDTO dogDTO, UserDetails user) {
		User userDB = userService.findByEmail(user.getUsername());
		repository.save(createDogEntity(dogDTO, userDB));
	}
	
	public void atualizarDog(DogDTO dogDTO, Long id, UserDetails user) {
		User userDB = userService.findByEmail(user.getUsername());
		Dog dog = getDog(id, userDB);
		repository.save(atualizarDogEntity(dogDTO, dog));
	}
	
	public Dog atualizarDogEntity(DogDTO dogDTO, Dog dog) {
		dog.setIdade(dogDTO.idade());
		dog.setName(dogDTO.nome());
		dog.setPeso(dogDTO.peso());
		dog.setPorte(dogDTO.porte());
		dog.setRaca(dogDTO.raca());
		return dog;
	}
	
	public Dog createDogEntity(DogDTO dogDTO, User user) {
		return Dog.builder()
			.idade(dogDTO.idade())
			.name(dogDTO.nome())
			.peso(dogDTO.peso())
			.raca(dogDTO.raca())
			.porte(dogDTO.porte())
			.user(user)
		.build();
	}

	public List<Dog> getAllDogs(UserDetails user) {
		User userDB = userService.findByEmail(user.getUsername());
		return repository.findByUser(userDB);
	}

	public Dog getDog(Long id, UserDetails user) {
		User userDB = userService.findByEmail(user.getUsername());
		return repository.findByUserAndId(userDB, id);
	}
}
