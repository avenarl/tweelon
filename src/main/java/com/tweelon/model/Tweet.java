/**************************************************************************************
 * Tweet.java																																					* 
 *                         																														*
 * Author: avenarl																																		*
 * Created on: 05-14-23																																*
 *																																										*
 * Description: A Tweet entity representing a tweet in the Tweelon Application.	      *
 * 						  This model holds information about a tweet, including the 	          *
 *							author (user), content, and timestamps for creation and updates.      *
 * 																																									  *
 * Feature: 																																					*
 * 		- Post a new tweet.																														  *
 * 		- Retrieve a tweet by ID.																												*
 * 		- Retrieve a list of tweets for a user.																					*
 * 		- Retrieve a list of tweets from users being followed.													*
 * 		- Update a tweet if the user is the author.																			*
 * 		- Delete a tweet if the user is the author.																			*
 *																																										*
 **************************************************************************************/

package com.tweelon.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tweets", schema = "public")
public class Tweet {

 /***********************
  *										  *
  *   Column Mappings   *
  *										  *		
  ***********************/

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(nullable = false)
  private String content;

  @Column(
      name = "created_at",
      nullable = false,
      columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private LocalDateTime createdAt;

  @Column(
      name = "updated_at",
      nullable = false,
      columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private LocalDateTime updatedAt;

  /********************************
	 *															*	
	 * 	Getter and Setter Methods	  *
	 * 														  *
	 ********************************/

	// ID
	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	// User ID
	public User getUser(){
		return user;
	}

	public void setUser(User user){
		this.user = user;
	}

	// Content
	public String getContent(){
		return content;
	}

	public void setContent(String content){
		this.content = content;
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
