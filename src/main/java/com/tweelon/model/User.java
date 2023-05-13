/*************************************************************************************
 * User.java 																																				 *
 *																																									 *
 * Author: avenarl																																	 *
 * Created on: 05-14-23																															 *
 *																																									 *
 * Description: A User entity representing a user in the Tweelon Application.        *
 * 						  This model holds information about a user's account, including       *
 * 						  their username, email, password, display name, bio, profile image,   *
 * 						  and timestamps for creation and updates.                             *
 *                                                                                   *
 * Feature:                                                                          *
 * 		- Register a new user.                                                         *
 * 		- Authenticate a user.                                                         *
 * 		- Update user profie.                                                          *
 * 		- Get user details.                                                            *
 * 		- Delete a user account.                                                       *
 *                                                                                   *
 *************************************************************************************/

package com.tweelon.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user", schema = "public")
public class User {

	/***********************
	 *										 *
	 *   Column Mappings   *
	 *										 *		
	 ***********************/

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(name = "display_name")
	private String displayName;

	@Column(columnDefinition = "TEXT")
	private String bio;

	@Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdAt;

	@Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMPJKI")
	private LocalDateTime updatedAt;


	/*******************************
	 *														 *	
	 * 	Getter and Setter Methods	 *
	 * 														 *
	 *******************************/
	
	// ID
	public Long getId() {
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	// Username
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	// Email
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}

	// Password
	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password = password;
	}

	// Display Name
	public String getDisplayName(){
		return displayName;
	}

	public void setDisplayName(String displayName){
		this.displayName = displayName;
	}

	// Bio
	public String getBio(){
		return bio;
	}

	public void setBio(String bio){
		this.bio = bio;
	}

	// Created At
	public LocalDateTime getCreatedAt(){
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt){
		this.createdAt = createdAt;
	}

	// Updated At
	public LocalDateTime getUpdatedAt(){
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt){
		this.updatedAt = updatedAt;
	} 

}
