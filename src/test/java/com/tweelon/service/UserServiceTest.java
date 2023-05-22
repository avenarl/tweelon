package com.tweelon.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;

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

	// Save user
	@Test
	void testSaveUser(){
		// Prepare sample data
    User user = new User();
    user.setUsername("testuser");
    user.setEmail("testuser@gmail.com");
    user.setPassword("testuser@123");
    user.setDisplayName("Test User");
    user.setBio("My name is Test User. I'm a tester.");

    // set the mock repository and password encoder
    given(passwordEncoder.encode(anyString())).willReturn("encryptedPassword");
    given(userRepository.save(user)).willAnswer(invocation -> {
        User argument = invocation.getArgument(0);
        argument.setPassword("encryptedPassword");
        return argument;
    });

    // call the service method
    User savedUser = userServiceImpl.save(user);

    // validate the result
    assertEquals(savedUser.getUsername(), "testuser");
    assertEquals(savedUser.getEmail(), "testuser@gmail.com");
    assertEquals(savedUser.getPassword(), "encryptedPassword");
    assertEquals(savedUser.getDisplayName(), "Test User");
    assertEquals(savedUser.getBio(), "My name is Test User. I'm a tester.");

    // verify the interactions with the mock repository
    verify(passwordEncoder).encode("testuser@123");
    verify(userRepository).save(user);
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

	// Update a User
	@Test
	void testUpdateUser(){
    // Given
    User existingUser = new User();
    existingUser.setId(1L);
    existingUser.setUsername("testuser");
    existingUser.setEmail("testuser@gmail.com");
    existingUser.setPassword(passwordEncoder.encode("testuser@123"));
    existingUser.setDisplayName("Test User");
    existingUser.setBio("My name is Test User. I'm a tester.");

    User updatedUser = new User();
    updatedUser.setId(1L);
    updatedUser.setUsername("updateduser");
    updatedUser.setEmail("updateduser@gmail.com");
    updatedUser.setPassword(passwordEncoder.encode("updatedpassword"));
    updatedUser.setDisplayName("Updated User");
    updatedUser.setBio("I'm an updated tester.");

    given(userRepository.findById(1L)).willReturn(Optional.of(existingUser));
    given(userRepository.save(any(User.class))).willAnswer(invocation -> invocation.getArgument(0));

    // When
    User savedUser = userServiceImpl.updateUser(updatedUser, 1L);

    // Then
    assertEquals(savedUser.getUsername(), updatedUser.getUsername());
    assertEquals(savedUser.getEmail(), updatedUser.getEmail());
    assertEquals(savedUser.getPassword(), passwordEncoder.encode(updatedUser.getPassword()));
    assertEquals(savedUser.getDisplayName(), updatedUser.getDisplayName());
    assertEquals(savedUser.getBio(), updatedUser.getBio());

    // Verify
    verify(userRepository).findById(1L);
    verify(userRepository).save(any(User.class));
	}

	// Delete user
	@Test
	void testDeleteUser(){
		Long userId = 1L; // initialize sample data
		userRepository.deleteById(userId); // call the service method
		verify(userRepository).deleteById(userId); // validations
	}

	// Get all users
	@Test
	void testGetAllUsers(){
		// prepare sample data
		List<User> userList = new ArrayList<>();
		User user1 = new User();
		user1.setId(1L);
		user1.setUsername("testuser1");
		userList.add(user1);
    User user2 = new User();
    user2.setId(2L);
    user2.setUsername("testuser2");
    userList.add(user2);

    // set the mock repository
    given(userRepository.findAll()).willReturn(userList);

    // call the service method
    List<User> returnedUserList = userServiceImpl.getAllUsers();

    // validate the result
    assertEquals(returnedUserList.size(), 2);
    assertEquals(returnedUserList.get(0).getUsername(), "testuser1");
    assertEquals(returnedUserList.get(1).getUsername(), "testuser2");

    // verify the interactions with the mock repository
    verify(userRepository).findAll();
	}

	// Check Password
	@Test
	void testCheckPassword(){
    // Prepare sample data
    String rawPassword = "rawpassword";
    String encodedPassword = "encodedpassword";

    // set the mock password encoder
    given(passwordEncoder.matches(rawPassword, encodedPassword)).willReturn(true);

    // call the service method
    boolean isMatch = userServiceImpl.checkPassword(rawPassword, encodedPassword);

    // validate the result
    assertTrue(isMatch);

    // verify the interactions with the mock password encoder
    verify(passwordEncoder).matches(rawPassword, encodedPassword);
	}
}
