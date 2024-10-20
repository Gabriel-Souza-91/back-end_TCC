package com.dog.api_rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dog.api_rest.model.Dog;
import com.dog.api_rest.model.User;



@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {
	
	List<Dog> findByUser(User user);
	
	Dog findByUserAndId(User user, Long id);
}
