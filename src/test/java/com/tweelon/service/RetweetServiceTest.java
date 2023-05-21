package com.tweelon.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import static org.mockito.BDDMockito.given;
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
	@Test
	void testGetAllRetweets(){
		    // Given
    List<Retweet> retweetList = new ArrayList<>();

    Retweet retweet1 = new Retweet();
    retweet1.setId(1L);
    retweetList.add(retweet1);

    Retweet retweet2 = new Retweet();
    retweet2.setId(2L);
    retweetList.add(retweet2);

    given(retweetRepository.findAll()).willReturn(retweetList);

    // When
    List<Retweet> returnedRetweets = retweetServiceImpl.getAllRetweets();

    // Then
    assertEquals(returnedRetweets.size(), 2);
    assertEquals(returnedRetweets.get(0).getId(), retweet1.getId());
    assertEquals(returnedRetweets.get(1).getId(), retweet2.getId());

    verify(retweetRepository).findAll();
	}
	// Get retweets by user id
	@Test
	void testGetRetweetsByUserId(){
	  // Given
    Long userId = 1L;
    User user = new User();
    user.setId(userId);

    Retweet retweet1 = new Retweet();
    retweet1.setId(1L);
    retweet1.setUserId(user);
    Retweet retweet2 = new Retweet();
    retweet2.setId(2L);
    retweet2.setUserId(user);

    List<Retweet> retweetList = Arrays.asList(retweet1, retweet2);

    given(retweetRepository.findByUserId(userId)).willReturn(retweetList);

    // When
    List<Retweet> returnedRetweets = retweetServiceImpl.getRetweetsByUserId(userId);

    // Then
    assertEquals(2, returnedRetweets.size());
    assertEquals(userId, returnedRetweets.get(0).getUserId().getId());
    assertEquals(userId, returnedRetweets.get(1).getUserId().getId());

    verify(retweetRepository).findByUserId(userId);
	}
	// Get retweets by tweet id

















}
