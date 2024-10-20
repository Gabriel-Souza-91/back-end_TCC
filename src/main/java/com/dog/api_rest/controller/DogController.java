package com.dog.api_rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dog.api_rest.model.Dog;
import com.dog.api_rest.model.dto.DogDTO;
import com.dog.api_rest.service.DogService;

@RestController
@RequestMapping("/dogs")
public class DogController {
	
	@Autowired
	private DogService service;
	
	@PostMapping
	public ResponseEntity<Void> novoDog(@RequestBody DogDTO dogDTO) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		service.novoDog(dogDTO, user);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizarDog(@PathVariable Long id, @RequestBody DogDTO dogDTO) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		service.atualizarDog(dogDTO, id, user);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<Dog>> getAllDogs() {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ResponseEntity.ok(service.getAllDogs(user));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Dog> getDog(@PathVariable Long id) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ResponseEntity.ok(service.getDog(id, user));
	}
	
}
