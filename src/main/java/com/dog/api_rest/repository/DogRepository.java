package com.dog.api_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dog.api_rest.model.Dog;


public interface DogRepository extends JpaRepository<Dog, Long> {
	
}
