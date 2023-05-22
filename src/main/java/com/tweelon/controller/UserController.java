package com.tweelon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweelon.model.User;
import com.tweelon.service.UserService;

@RestController
@RequestMapping("/api/v1/user/") 
public class UserController {

	@Autowired
	public UserService userService; // Access methods from user service

	// Fetch all users
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}	

	// Fetch single user
	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id){
		return userService.getUserById(id);
	}

	// Create user
	@PostMapping
	public User createUser(@RequestBody User user){
		return userService.createUser(user);
	}

	// Delete user
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id){
		userService.deleteUser(id);
	}

	// Update user
	@PutMapping("/{id}")
	public User updateUser(@RequestBody User user, @PathVariable Long id){
		return userService.updateUser(user, id);
	}
}
