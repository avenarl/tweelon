package com.tweelon.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
	@Test
	void testUpdateTweet(){
		// Create first a User
		User user = new User();
		user.setId(1L);
		given(userService.getUserById(1L)).willReturn(user);

    // Given
    Tweet existingTweet = new Tweet();
    existingTweet.setId(1L);
    existingTweet.setContent("My first tweet as a tester.");
		existingTweet.setUser(user);

    Tweet updatedTweet = new Tweet();
		updatedTweet.setId(1L);
    updatedTweet.setContent("My edited tweet as a tester.");

    given(tweetRepository.findById(1L)).willReturn(Optional.of(existingTweet));
    given(tweetRepository.save(any(Tweet.class))).willAnswer(invocation -> invocation.getArgument(0));

    // When
    Tweet savedTweet = tweetServiceImpl.updateTweet(updatedTweet, 1L);

    // Then
    assertEquals(savedTweet.getId(), updatedTweet.getId());
    assertEquals(savedTweet.getContent(), updatedTweet.getContent());

    // Verify
    verify(tweetRepository).findById(1L);
    verify(tweetRepository).save(any(Tweet.class));
	}
	// Delete Tweet
	@Test
	void testDeleteTweet(){
		Long tweetId = 1L;
		tweetRepository.deleteById(tweetId);
		verify(tweetRepository).deleteById(tweetId);
	}
	// Get All Tweets
	@Test
	void testGetAllTweets(){
		// prepare sample data
		List<Tweet> tweetList = new ArrayList<>();
		User user1 = new User();
		user1.setId(1L);
		Tweet tweet1 = new Tweet();
		tweet1.setUser(user1);
		tweet1.setContent("My tweet.");
		tweetList.add(tweet1);

		User user2 = new User();
		user2.setId(2L);
		Tweet tweet2 = new Tweet();
		tweet2.setUser(user2);
		tweet2.setContent("The other tweet.");
		tweetList.add(tweet2);

		given(tweetRepository.findAll()).willReturn(tweetList);

		List<Tweet> returnedTweetList = tweetServiceImpl.getAllTweets();

		assertEquals(returnedTweetList.size(), 2);
		assertEquals(returnedTweetList.get(0).getContent(), "My tweet.");
		assertEquals(returnedTweetList.get(1).getContent(), "The other tweet.");

		verify(tweetRepository).findAll();
	}
	// Get Tweet by User ID
	@Test	
	void testGetTweetByUserId(){
		List<Tweet> tweetList = new ArrayList<>(); 

		User user1 = new User();
		user1.setId(1L);

		Tweet tweet1 = new Tweet();
		tweet1.setUser(user1);
		tweet1.setContent("This tweet.");
		tweetList.add(tweet1);

		given(tweetRepository.findByUserId(1L)).willReturn(tweetList);

		List<Tweet> returnedTweet = tweetServiceImpl.getTweetByUserId(1L);
		assertNotNull(returnedTweet);
		assertEquals(1, returnedTweet.size());
		assertEquals(tweet1, returnedTweet.get(0));

		verify(tweetRepository).findByUserId(1L);
	}
}

