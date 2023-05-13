/**************************************************************************************
 * TweetService.java 																																	*
 *																																										*
 * Author: avenarl																																		*
 * Created on: 05-14-23																																*
 *																																										*
 * Description: This interface defines the business logic for managing tweets in the 	*
 * 							Tweelon Application. It provides methods for creating, retrieving, 		*
 * 							updating and deleting tweets, as well as other tweet-related 					*
 * 							operations.																														*
 * 																																										*
 **************************************************************************************/

package com.tweelon.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tweelon.model.Tweet;

@Service
public interface TweetService {

	Tweet createTweet(Tweet tweet, Long userId); // Create a Tweet by its user ID.
	Tweet getTweetById(Long id); // Retrieve a Tweet by its ID.
	Tweet updateTweet(Tweet tweet, Long userId); // Edit a Tweet by its user ID.
	void deleteTweet(Long id); // Delete a Tweet by its ID.
	List<Tweet> getAllTweets(); // Retrieve all Tweets in the system.
	List<Tweet> getTweetByUserId(Long userId); // Retrieve a Tweet by its user ID.
	
}	
