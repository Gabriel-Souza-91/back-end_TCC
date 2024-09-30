package com.dog.api_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dog.api_rest.model.Dog;


@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {
	
}
