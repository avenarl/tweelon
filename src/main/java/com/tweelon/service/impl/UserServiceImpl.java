package com.tweelon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tweelon.model.User;
import com.tweelon.repository.UserRepository;
import com.tweelon.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	// Inject UserRepository instance
	@Autowired
	private UserRepository userRepository;

	// Inject Password Encoder instance
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	// Constructor for testing
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

  public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

	@Override
  public User save(User user){
		// Hash the password before saving the user
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		// Save user
    return userRepository.save(user);
  }

	@Override
	public User createUser(User user) {
		// Hash the password before saving the user
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		// Save and return the new user
		return userRepository.save(user);
	}

	@Override
	public User getUserById(Long id){
		// Find the user by ID and return it, or null if not found
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User updateUser(User user){
		// Hash the password before updating the user
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		// Save and return the updated user
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Long id){
		// Delete the user by ID
		userRepository.deleteById(id);
	} 

	@Override
	public List<User> getAllUsers(){
		// Return the list of all users
		return userRepository.findAll();
	}

	// Validation
	public boolean checkPassword(String rawPassword, String encodedPassword){
		// Compare the raw password with the hashed password
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}
}
