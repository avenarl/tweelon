package com.tweelon.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tweelon.model.User;
import com.tweelon.repository.UserRepository;
import com.tweelon.service.UserService;

import jakarta.persistence.EntityNotFoundException;

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
	public User loginUser(User user){
		Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
  	if (!optionalUser.isPresent()) {
			throw new RuntimeException("User not found with username: " + user.getUsername());
		}

		User existingUser = optionalUser.get();
		if (!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
			throw new RuntimeException("Invalid password");
		}

		return existingUser;
	}

	@Override
	public User registerUser(User user){
		user.setPassword(passwordEncoder.encode(user.getPassword()));
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
	public User updateUser(User user, Long id){
		 // Fetch the user from the database
    Optional<User> existingUserOptional = userRepository.findById(id);

    if (existingUserOptional.isPresent()) {
        User existingUser = existingUserOptional.get();

        // Update fields
        if (user.getUsername() != null) {
            existingUser.setUsername(user.getUsername());
        }

        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }

        if (user.getPassword() != null) {
            existingUser.setPassword(user.getPassword());
        }

        if (user.getDisplayName() != null) {
            existingUser.setDisplayName(user.getDisplayName());
        }
        
				if (user.getBio() != null) {
            existingUser.setBio(user.getBio());
        }
        // Save the updated user back to the database
        return userRepository.save(existingUser);
    } else {
        // If the user is not found, throw an exception
        throw new EntityNotFoundException("User not found with id: " + id);
    }
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
