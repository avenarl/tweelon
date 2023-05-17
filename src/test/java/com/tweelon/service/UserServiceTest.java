package com.tweelon.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tweelon.model.User;
import com.tweelon.repository.UserRepository;
import com.tweelon.service.impl.UserServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	
	@Mock
	private UserRepository userRepository;

	@Mock
  private BCryptPasswordEncoder passwordEncoder;

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	@BeforeEach
  void initUseCase(){
    userServiceImpl = new UserServiceImpl(userRepository, passwordEncoder);
  }

	@Test
	public void testGetUserById(){
		User user = new User(); // Creates new object User
		user.setId(1L); // the obect user has id of 1 (Long)
		user.setUsername("testuser"); // the object user has a username "testuser"
		given(userRepository.findById(1L)).willReturn(Optional.of(user)); // return an Optional object that has the 'user'

		User returnedUser = userServiceImpl.getUserById(1L); // call the method in service impl and store to a variable of User object

		assertEquals(returnedUser.getUsername(), "testuser"); // JUnit: validates if two parameters are equal
	}

	// Creating a User 
	@Test
	void testCreateUser(){
		User user = new User();
		user.setUsername("testuser");
		user.setEmail("testuser@gmail.com");
		user.setPassword("testuser@123");
		user.setDisplayName("Test User");
		user.setBio("My name is Test User. I'm a tester.");

	  LocalDateTime createdAt = LocalDateTime.of(2023, 5, 1, 0, 0);  // adjust as necessary
    LocalDateTime updatedAt = LocalDateTime.of(2023, 5, 2, 0, 0);  // adjust as necessary

    user.setCreatedAt(createdAt);
    user.setUpdatedAt(updatedAt);

    given(userRepository.save(user)).willAnswer(invocation -> {
        User argument = invocation.getArgument(0);
        argument.setUpdatedAt(updatedAt.plusDays(1));  // update the updatedAt field
        return argument;
    });

		given(passwordEncoder.encode(anyString())).willReturn("testuser@123");

		User savedUser = userServiceImpl.createUser(user);

		assertEquals(savedUser.getUsername(), "testuser");
		assertEquals(savedUser.getEmail(), "testuser@gmail.com");
		assertEquals(savedUser.getPassword(), "testuser@123");
		assertEquals(savedUser.getDisplayName(), "Test User");
		assertEquals(savedUser.getBio(), "My name is Test User. I'm a tester.");
		assertEquals(savedUser.getCreatedAt(), createdAt);
    assertEquals(savedUser.getUpdatedAt(), updatedAt.plusDays(1));
		verify(userRepository).save(user);
	}
}
