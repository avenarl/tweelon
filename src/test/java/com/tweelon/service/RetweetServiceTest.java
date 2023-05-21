package com.tweelon.service;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tweelon.model.User;
import com.tweelon.model.Tweet;
import com.tweelon.model.Retweet;
import com.tweelon.service.impl.RetweetServiceImpl;
import com.tweelon.repository.UserRepository;
import com.tweelon.repository.TweetRepository;
import com.tweelon.repository.RetweetRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class RetweetServiceTest {

	@InjectMocks
	private RetweetServiceImpl retweetServiceImpl;

	@Mock
	private RetweetService retweetService;

	@Mock
	private UserRepository userRepository;

	@Mock
	private TweetRepository tweetRepository;

	@Mock
	private RetweetRepository retweetRepository;

	@BeforeEach
	public void setup(){
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreateRetweet(){
	  // Given
    Long userId = 1L;
    Long tweetId = 1L;
    User user = new User();
    user.setId(userId);
    Tweet tweet = new Tweet();
    tweet.setId(tweetId);
    Retweet retweet = new Retweet();
    retweet.setUserId(user);
    retweet.setTweetId(tweet);

    when(userRepository.findById(userId)).thenReturn(Optional.of(user));
    when(tweetRepository.findById(tweetId)).thenReturn(Optional.of(tweet));
    when(retweetRepository.save(any(Retweet.class))).thenReturn(retweet);

    // When
    Retweet result = retweetServiceImpl.createRetweet(userId, tweetId);

    // Then
    assertEquals(result.getUserId(), user);
    assertEquals(result.getTweetId(), tweet);

    // Verify
    verify(userRepository, times(1)).findById(userId);
    verify(tweetRepository, times(1)).findById(tweetId);
    verify(retweetRepository, times(1)).save(any(Retweet.class));
	}

	// Delete retweet
	@Test
	void testDeleteRetweet() {
		Long tweetId = 1L;
		tweetRepository.deleteById(tweetId);
		verify(tweetRepository).deleteById(tweetId);
	}
	// Get all retweets
	// Get retweets by user id
	// Get retweets by tweet id

















}
