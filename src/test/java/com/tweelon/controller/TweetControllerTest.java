package com.tweelon.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.tweelon.model.Tweet;
import com.tweelon.model.User;
import com.tweelon.service.TweetService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
    "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration"
})
public class TweetControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TweetService tweetService;

	// Get all tweet
	@Test
	public void testGetAllTweets() throws Exception {
		User user1 = new User();
		user1.setUsername("testuser1");

		Tweet tweet1 = new Tweet();
		tweet1.setId(1L);
		tweet1.setUser(user1);
		tweet1.setContent("Tweet 1");


		User user2 = new User();
		user2.setUsername("testuser2");
	
		Tweet tweet2 = new Tweet();
		tweet2.setId(2L);
		tweet2.setUser(user2);
		tweet2.setContent("Tweet 2");

		List<Tweet> tweetList = Arrays.asList(tweet1, tweet2);
		when(tweetService.getAllTweets()).thenReturn(tweetList);

		mockMvc.perform(get("/api/v1/tweet/tweets")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$[0].id", is(1)))
		.andExpect(jsonPath("$[0].content", is("Tweet 1")))
		.andExpect(jsonPath("$[1].id", is(2)))
		.andExpect(jsonPath("$[1].content", is("Tweet 2")));

		verify(tweetService, times(1)).getAllTweets();
	}
	// Create tweets
	// Update a tweet
	// Delete a tweet by id
	// Get single tweet by user id
	// Get single tweet by user id
}
