/**************************************************************************************
 * RetweetService.java																															  *
 *																																									  *
 * Author: avenarl																																	  *
 * Created on: 05-14-23																															  *
 *																																									  *
 * Description: This interface defines the business logic for managing retweets 		  *
 *   					  in the Tweelon Application. It provides methods for creating, 			  *
 *   					  deleting, and retrieving retweets based on user or tweet IDs.				  *
 *																																									  *
 **************************************************************************************/

package com.tweelon.service;

import java.util.List;

import com.tweelon.model.Retweet;

public interface RetweetService {
	
	Retweet createRetweet(Long userId, Long tweetId); // Retweet a Tweet by its user ID and the associated tweet ID.
	void deleteRetweet(Long retweetId); // Delete a Retweet by its retweet ID.
	List<Retweet> getAllRetweets(); // Retrieve all Retweets in the system.
	List<Retweet> getRetweetsByUserId(Long userId); // Retrieves a Retweet by its user ID.
	List<Retweet> getRetweetsByTweetId(Long tweetId); // Retrieves a Retweet by its tweet ID.
	
}
