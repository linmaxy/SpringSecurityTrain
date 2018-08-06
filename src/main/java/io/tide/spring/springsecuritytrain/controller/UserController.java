package io.tide.spring.springsecuritytrain.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.tide.spring.springsecuritytrain.domain.User;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@PostMapping("/create")
	public User createUser(User user){
		return user;
	}

	@GetMapping("/listAllUsers")
	public List<User> listAllUsers(){
		List<User> result = new ArrayList<>();
		
		result.add(new User("User1", "qwe", "MEMBER"));
		result.add(new User("User2", "qwe", "MEMBER"));
		return result;
	}

}
