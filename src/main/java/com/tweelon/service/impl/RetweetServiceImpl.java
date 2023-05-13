/**************************************************************************************
 * RetweetServiceImpl.java																														*
 *																																										*
 * Author: avenarl																																		*
 * Created on: 05-12-23																																*
 *																																										*
 * Description: This class provides the implementation of the RetweetService 					*
 *              interface. It handles the business logic for managing retweets in     *
 *              the Tweelon application. It provides methods for creating,            *
 *              retrieving, updating, and deleting retweets,as well as retrieving     *
 *              retweets by user or tweet.																					  *
 *																																										*
 **************************************************************************************/

package com.tweelon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweelon.model.Retweet;
import com.tweelon.model.Tweet;
import com.tweelon.model.User;
import com.tweelon.repository.RetweetRepository;
import com.tweelon.repository.TweetRepository;
import com.tweelon.repository.UserRepository;
import com.tweelon.service.RetweetService;

@Service
public class RetweetServiceImpl implements RetweetService {

	@Autowired
	private RetweetRepository retweetRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TweetRepository tweetRepository;

	@Override
	public Retweet createRetweet(Long userId, Long tweetId) {
	  // Find user by user ID	
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));
		// Find tweet by tweet ID
    Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new RuntimeException("Tweet not found."));

		// Create new Retweet object
    Retweet retweet = new Retweet();
		// Set user ID and tweet ID
    retweet.setUserId(user);
    retweet.setTweetId(tweet);
 
		// Save and return the retweet
    return retweetRepository.save(retweet);
	}

	@Override
	public void deleteRetweet(Long retweetId) {
		retweetRepository.deleteById(retweetId);
	}

	@Override
	public List<Retweet> getAllRetweets() {
		return retweetRepository.findAll();
	}	

	@Override
	public List<Retweet> getRetweetsByUserId(Long userId) {
		return retweetRepository.findByUserId(userId);
	}

	@Override
	public List<Retweet> getRetweetsByTweetId(Long tweetId) {
		return retweetRepository.findByTweetId(tweetId);
	}	

}

















