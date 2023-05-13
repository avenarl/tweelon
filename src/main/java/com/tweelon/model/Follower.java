/**************************************************************************************
 * Follower.java 																																			*
 *             																																				*
 * Author: avenarl                                                                    *
 * Created on: 05-14-23                                                               *
 *                                                                                    *
 * Description: A Follower entity representing a user's followers in the              *
 * 						 Tweelon Application. This model holds information about                *
 * 						 a user and the user they follow, and the timestamp for                 *
 * 						 when the user started following another user.                          *
 * 							 																																		  *
 * Feature:                                                                           *
 * 		- Follow another user.																													*
 * 		- Unfollow another user.                                                        *
 * 		- Retrieve a list of followers for a user.                                      *
 * 		- Retrieve a list of a users a user is following.                               *
 *                                                                                    *
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
@Table(name = "followers", schema = "public")
public class Follower {
  
	/***********************
	 *										 *
	 *   Column Mappings   *
	 *										 *		
	 ***********************/

	@Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User userId;

  @ManyToOne
  @JoinColumn(name = "following_id", nullable = false)
  private User followingId;

  @Column(
      name = "created_at",
      nullable = false,
      columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private LocalDateTime createdAt;


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
	public User getUserId(){
		return userId;
	}

	public void setUserId(User userId){
		this.userId = userId;
	}

	// Following ID
	public User getFollowingId() {
		return followingId;
	}

	public void setFollowingId(User followingId){
		this.followingId = followingId;
	}

	// Created At
	public LocalDateTime getCreatedAt(){
		return createdAt;
	} 

	public void setCreatedAt(LocalDateTime createdAt){
		this.createdAt = createdAt;
	}
}
