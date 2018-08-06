package io.tide.spring.springsecuritytrain.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
	
	private String name;
	
	private String password;
	
	private String role;

}
