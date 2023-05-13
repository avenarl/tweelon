/**************************************************************************************
 * UserService.java 																																	*
 *																																										*
 * Author: avenarl																																		*
 * Created on: 05-12-23																																*
 *																																										*
 * Description: This interface defines the service layer for the User entity in the 	*
 * 						  Tweelon Application. It provides methods for creating, retrieving, 		*
 * 						  updating and deleting User records, as well as any other business 		*
 * 						  logic related to User management.																			*
 * 																																										*
 **************************************************************************************/

package com.tweelon.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tweelon.model.User;

@Service
public interface UserService {
	
	User save(User user); // Save a User.
	User createUser(User user); // Create a User.
	User getUserById(Long id); // Retrieve a User by its ID.
	User updateUser(User user); // Edit a User. 
	void deleteUser(Long id); // Delete a User by its ID. */
	List<User> getAllUsers(); // Retrieve all Users in the system.
	
}