package com.tweelon.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tweelon.model.User;
import com.tweelon.repository.UserRepository;
import com.tweelon.service.UserService;

@RestController
@RequestMapping("/api/v1/user") 
public class UserController {

	@Autowired
	public UserService userService; // Access methods from user service
	
	@Autowired
	public UserRepository userRepository;

	// Login
	@PostMapping("/login")
	public ResponseEntity<User> loginUser(@RequestBody User user){

		Optional<User> usernameEntry = userRepository.findByUsername(user.getUsername());

		if(usernameEntry.isPresent()){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect Username");
		}

		User existingUser = usernameEntry.get();
		if(!existingUser.getPassword().equals(user.getPassword())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect Password");
		}

		return new ResponseEntity<>(existingUser, HttpStatus.OK);

	}

	// Register
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user){

		Optional<User> usernameEntry = userRepository.findByUsername(user.getUsername());
		Optional<User> emailEntry = userRepository.findByEmail(user.getEmail());

		if(usernameEntry.isPresent()){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
		}
		if(emailEntry.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
		}

		User newUser = userService.registerUser(user);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}

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
