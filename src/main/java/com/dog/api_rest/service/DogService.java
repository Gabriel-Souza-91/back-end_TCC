package com.dog.api_rest.service;

import static java.util.Objects.nonNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dog.api_rest.config.bussines.BusinessException;
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
		List<Dog> allDogs = getAllDogs(userDB);
		if(nonNull(allDogs) && allDogs.size() >= 4) {
			throw new BusinessException("Você atingiu o limite de cachorros.");
		}
		repository.save(createDogEntity(dogDTO, userDB));
	}
	
	@Transactional(readOnly = false)
	public void deleteDog(Long id, UserDetails user) {
		User userDB = userService.findByEmail(user.getUsername());
		repository.deleteByUserAndId(userDB, id);
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
