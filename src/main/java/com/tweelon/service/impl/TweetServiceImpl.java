/*
 * TweetServiceImpl.java																															
 *																																										
 * Author: avenarl																																		
 * Created on: 05-14-23																																
 *																																										
 * Description: This class provides the implementation of the TweetService interface.	
 *              It handles the business logic for managing tweets in the Tweelon 			
 *              application. It provides methods for creating, retrieving, updating, 	
 *              and deleting tweets, as well as retrieving tweets by user or other 		
 *              criteria.																															
 *																																										
 */

package com.tweelon.service.impl;

import com.tweelon.model.Tweet;
import com.tweelon.model.User;
import com.tweelon.repository.TweetRepository;
import com.tweelon.service.TweetService;
import com.tweelon.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetServiceImpl implements  TweetService {
		@Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private UserService userService;

    @Override
    public Tweet createTweet(Tweet tweet, Long userId) {
        User user = userService.getUserById(userId);
        tweet.setUser(user);
        return tweetRepository.save(tweet);
    }

    @Override
    public Tweet getTweetById(Long id) {
        return tweetRepository.findById(id).orElse(null);
    }

    @Override
    public Tweet updateTweet(Tweet tweet, Long userId) {
        User user = userService.getUserById(userId);
        Tweet existingTweet = tweetRepository.findById(tweet.getId()).orElse(null);

        if (existingTweet != null && existingTweet.getUser().getId().equals(user.getId())) {
            existingTweet.setContent(tweet.getContent());
            return tweetRepository.save(existingTweet);
        } else {
            return null;
        }
    }

    @Override
    public void deleteTweet(Long id) {
        tweetRepository.deleteById(id);
    }

    @Override
    public List<Tweet> getAllTweets() {
        return tweetRepository.findAll();
    }

    @Override
    public List<Tweet> getTweetByUserId(Long userId) {
        return tweetRepository.findByUserId(userId);
    }
}
