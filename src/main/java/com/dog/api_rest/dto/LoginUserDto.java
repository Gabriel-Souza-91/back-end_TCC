package com.dog.api_rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserDto {
	
	private String email;
    
    private String password;
}
