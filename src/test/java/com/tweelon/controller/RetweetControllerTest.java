package com.tweelon.controller;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.tweelon.repository.RetweetRepository;
import com.tweelon.service.RetweetService;
import com.tweelon.service.impl.RetweetServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tweelon.model.Retweet;
import com.tweelon.model.Tweet;
import com.tweelon.model.User;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
    "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration"
})
public class RetweetControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RetweetService retweetService;

	@Mock
	private RetweetServiceImpl retweetServiceImpl;

	@Mock
	private RetweetRepository retweetRepository;

	// Create retweet
	@Test
	public void testCreateRetweet() throws Exception {

		User user = new User();
		user.setId(1L);
		user.setUsername("testuser");

		Tweet tweet = new Tweet();
		tweet.setId(1L);
		tweet.setUser(user);
		tweet.setContent("ReTweet 1");

		Retweet retweet = new Retweet();
		retweet.setId(1L);
		retweet.setTweetId(tweet);
		retweet.setUserId(user);
		
		when(retweetService.createRetweet(any(Long.class), any(Long.class))).thenReturn(retweet);

		mockMvc.perform(post("/api/v1/retweet/{userId}/{tweetId}", 1L, 1L)
			.contentType(MediaType.APPLICATION_JSON)
			.content(new ObjectMapper().writeValueAsString(retweet)))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.id", is(1)));

		verify(retweetService, times(1)).createRetweet(any(Long.class), any(Long.class));
	}

	// Delete retweet
	@Test
	void testDeleteRetweet(){
		Long retweetId = 1L;
		retweetRepository.deleteById(retweetId);
		verify(retweetRepository).deleteById(retweetId);
	}
	
	// Get all retweets
	@Test
	public void testGetAllRetweets() throws Exception {
		User user1 = new User();
		user1.setUsername("testuser1");

		Tweet tweet1 = new Tweet();
		tweet1.setId(1L);
		tweet1.setUser(user1);
		tweet1.setContent("tweet 1");

		Retweet retweet1 = new Retweet();
		retweet1.setId(1L);
		retweet1.setUserId(user1);
		retweet1.setTweetId(tweet1);

		User user2 = new User();
		user2.setUsername("testuser1");

		Tweet tweet2 = new Tweet();
		tweet2.setId(2L);
		tweet2.setUser(user1);
		tweet2.setContent("tweet 2");

		Retweet retweet2 = new Retweet();
		retweet2.setId(2L);
		retweet2.setUserId(user2);
		retweet2.setTweetId(tweet2);

		List<Retweet> retweetList = Arrays.asList(retweet1, retweet2);
		when(retweetService.getAllRetweets()).thenReturn(retweetList);

		mockMvc.perform(get("/api/v1/retweet/retweets")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(2)))
		  .andExpect(jsonPath("$[0].id", is(1)))
			.andExpect(jsonPath("$[1].id", is(2)));

		verify(retweetService, times(1)).getAllRetweets();
	}
	// Get all retweet by user id
	// Get all retweet by tweet id

}
