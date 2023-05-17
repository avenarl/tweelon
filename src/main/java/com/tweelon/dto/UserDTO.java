/**
 * TweetDTO.java
 *
 * Author: avenarl
 * Created on: 05-17-23
 *
 * Data Transfer Object for User entity.
 * This is used to encapsulate the User data and
 * prevent direct exposure of the domain entities 
 * to the client.
 *
 * Fields:
 * 1. Username - the username of the user
 * 2. Email - the email address of the user
 * 3. DisplayName - the display name of the user
 * 4. Bio - the short bio of the user
 *
 */

package com.tweelon.dto;

public class UserDTO {

	private String username;
	private String email;
	private String displayName;
	private String bio;

 	/**
   * Getter and Setter Methods
   */

	// Username	
  /**
   * Returns the username of the user.
   * @return username of the user
   */	

	public String getUsername() {
		return username;
	}

  /**
	 * Sets the username of the user.
   * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	// Email
	/**
	 * Returns the email of the user.
   * @return email the username to set
	 */
	public String getEmail(){
		return email;
	}

	/**
	 * Sets the email of the user.
   * @param email the username to set
	 */
	public void setEmail(String email){
		this.email = email;
	}

	// Display Name
	/**
   * Returns the display name of the user.
   * @return display name of the user
   */
	public String getDisplayName(){
		return displayName;
	}
  
	/**
   * Sets the display name of the user.
   * @param displayName the display name to set
   */
	public void setDisplayName(String displayName){
		this.displayName = displayName;
	}

	// Bio
  /**
   * Returns the bio of the user.
   * @return bio of the user
   */
	public String getBio(){
		return bio;
	}

  /**
   * Sets the bio of the user.
   * @param bio the bio to set
   */
	public void setBio(String bio){
		this.bio = bio;
	}

}
