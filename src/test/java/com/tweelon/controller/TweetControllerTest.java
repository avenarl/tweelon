package com.tweelon.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tweelon.model.Tweet;
import com.tweelon.model.User;
import com.tweelon.repository.TweetRepository;
import com.tweelon.repository.UserRepository;
import com.tweelon.service.TweetService;
import com.tweelon.service.impl.TweetServiceImpl;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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

    @Mock
    private TweetServiceImpl tweetServiceImpl;

    @Mock
    private TweetRepository tweetRepository;

    // Create tweets
    @Test
    public void testCreateTweet() throws Exception {

        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        Tweet tweet = new Tweet();
        tweet.setId(1L);
        tweet.setUser(user);
        tweet.setContent("The created tweet");

        when(tweetService.createTweet(any(Tweet.class), any(Long.class))).thenReturn(tweet);

        mockMvc.perform(post("/api/v1/tweet/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(tweet)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.content", is("The created tweet")));

        verify(tweetService, times(1)).createTweet(any(Tweet.class), any(Long.class));
    }

    // Update a tweet
    @Test
    public void testUpdateTweet() throws Exception {

        User user = new User();
        user.setId(1L);
        user.setUsername("testuser1");

        Tweet tweet = new Tweet();
        tweet.setId(1L);
        tweet.setUser(user);
        tweet.setContent("updated tweet");

        when(tweetService.updateTweet(any(Tweet.class), any(Long.class))).thenReturn(tweet);

        mockMvc.perform(put("/api/v1/tweet/1/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(tweet)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.content", is("updated tweet")));

        verify(tweetService, times(1)).updateTweet(any(Tweet.class), any(Long.class));
    }

    // Delete a tweet by id
    @Test
    void testDeleteTweet() {
        Long tweetId = 1L;
        tweetRepository.deleteById(tweetId);
        verify(tweetRepository).deleteById(tweetId);
    }

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

    // Get single tweet by user id
    @Test
    public void testGetTweetByUserId() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser1");

        Tweet tweet1 = new Tweet();
        tweet1.setId(1L);
        tweet1.setUser(user);
        tweet1.setContent("tweet 1");

        Tweet tweet2 = new Tweet();
        tweet2.setId(2L);
        tweet2.setUser(user);
        tweet2.setContent("tweet 2");

        List<Tweet> tweets = Arrays.asList(tweet1, tweet2);

        when(tweetService.getTweetByUserId(1L)).thenReturn(tweets);

        mockMvc.perform(get("/api/v1/tweet/user/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].content", is("tweet 1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].content", is("tweet 2")));

        verify(tweetService, times(1)).getTweetByUserId(1L);
    }

    // Get single tweet by id
    @Test
    public void testGetTweetById() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser1");

        Tweet tweet = new Tweet();
        tweet.setId(1L);
        tweet.setUser(user);
        tweet.setContent("tweet 1");

        when(tweetService.getTweetById(1L)).thenReturn(tweet);

        mockMvc.perform(get("/api/v1/tweet/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.content", is("tweet 1")));

        verify(tweetService, times(1)).getTweetById(1L);
    }

}
