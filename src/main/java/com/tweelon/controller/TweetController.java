/**************************************************************************************
 * TweetController.java 																															*
 *																																										*
 * Author: avenarl																																		*
 * Created on: 05-14-23																																*
 *																																										*
 * Description: A REST Controller for handling Tweet-related operations in the 				*
 * 						 Tweelon Application. This class maps endpoints for creating, 					*
 * 						 updating, deleting, and fetching tweets. 															*
 *																																										*
 **************************************************************************************/

package com.tweelon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweelon.model.Tweet;
import com.tweelon.service.TweetService;

@RestController
@RequestMapping("/api/v1/tweet")
public class TweetController {

	@Autowired
	public TweetService tweetService;

	// Create tweets
	@PostMapping
	public Tweet createTweet(@RequestBody Tweet tweet, @PathVariable Long userId){
		return tweetService.createTweet(tweet, userId);
	}

	// Update a tweet
	@PutMapping("/{tweetId}")
	public Tweet updateTweet(@RequestBody Tweet tweet, @PathVariable Long userId){
		return tweetService.updateTweet(tweet, userId);
	}

	// Delete a tweet
	@DeleteMapping("/{tweetId}")
	public void deleteTweet(@PathVariable Long id){
		tweetService.deleteTweet(id);
	}

	// Fetch single tweet by id 
	@GetMapping("/tweet/{tweetId}")
	public Tweet getTweetById(@PathVariable Long tweetId){
		return tweetService.getTweetById(tweetId);
	}

	// Fetch single tweet by user id
	@GetMapping("/tweet/{userId}")
	public  List<Tweet> getTweetByUserId(@PathVariable Long userId){
		return tweetService.getTweetByUserId(userId);
	}

	// Fetch all tweet
	@GetMapping("/tweets")
	public List<Tweet> getAllTweets(){
		return tweetService.getAllTweets();
	}

}
