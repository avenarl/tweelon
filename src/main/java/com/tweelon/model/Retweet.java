/**************************************************************************************
 * Retweet.java																																				* 
 *																																										*
 * Author: avenarl																																		*
 * Created on: 05-14-23																																*
 *																																										*
 * Description: A Retweet entity representing a user's retweeted tweets in the 				*
 * 						  Tweelon Application. This model holds information about a user				*
 * 						  and the tweet they retweeted, and the timestamp for when the					*
 * 						  user retweeted the tweet.																							*
 * 																																										*
 * Feature: 																																					*
 * 		- Retweet a tweet.																															*
 * 		- Unretweet a tweet.																														*
 * 		- Retrieve a list of tweets retweeted by a user.																*
 * 		- Retrieve a count of retweets for a tweet.																			*
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
@Table(name = "retweets", schema = "public")
public class Retweet {

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
  private User userId;

  @ManyToOne
	@JoinColumn(name = "tweet_id", nullable = false)
  private Tweet tweetId;

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

	// Tweet ID
	public Tweet getTweetId(){
		return tweetId;
	}

	public void setTweetId(Tweet tweetId){
  	this.tweetId = tweetId;
	}

	// Created At
	public LocalDateTime getCreatedAt(){
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt){
		this.createdAt = createdAt;
	}
}
