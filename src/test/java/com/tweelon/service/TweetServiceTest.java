package com.tweelon.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.tweelon.model.Tweet;
import com.tweelon.model.User;
import com.tweelon.repository.TweetRepository;
import com.tweelon.service.impl.TweetServiceImpl;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TweetServiceTest {

	@Mock
	private TweetRepository tweetRepository;

	@Mock
	private UserService userService;

	@InjectMocks
	private TweetServiceImpl tweetServiceImpl;

	// Create Tweet
	// Get Tweet by ID
	@Test
	public void testGetTweetById(){
		Tweet tweet = new Tweet(); // Creates new object tweet
		tweet.setId(1L); // 1L Id of tweet
		tweet.setContent("My first tweet as a tester."); // Content of tweet
		given(tweetRepository.findById(1L)).willReturn(Optional.of(tweet)); // find and return optional
		Tweet returnedTweet = tweetServiceImpl.getTweetById(1L); // calls the method in service impl and store to a variable of tweet object
		assertEquals(returnedTweet.getId(), 1L);	// validation
	}
	// Update Tweet	
	// Delete Tweet
	// Get All Tweets
	// Get Tweet by User ID
}

