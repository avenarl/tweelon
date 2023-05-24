package com.tweelon.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.tweelon.model.Follower;
import com.tweelon.service.FollowerService;
import com.tweelon.model.User;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Test;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
    "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration"
})
public class FollowerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FollowerService followerService;

	// Create follower
	@Test
	public void testFollowUser() throws Exception {
    User user = new User();
    user.setId(1L);
    user.setUsername("testuser");

    User following = new User();
    following.setId(2L);
    following.setUsername("testuser2");

    Follower follower = new Follower();
    follower.setId(1L);
    follower.setUserId(user);
    follower.setFollowingId(following);

    when(followerService.followUser(any(Long.class), any(Long.class))).thenReturn(follower);

    mockMvc.perform(post("/api/v1/follower/{userId}/{followingId}", 1L, 2L)
			.contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.id", is(1)));

    verify(followerService, times(1)).followUser(any(Long.class), any(Long.class));
	}

	// Delete follower
	@Test
	void testUnfollowUser() throws Exception{
		Long followingId = 1L;
		Long userId = 1L;
		doNothing().when(followerService).unfollowUser(userId, followingId);

		mockMvc.perform(delete("/api/v1/follower/1/1", userId, followingId)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());

		verify(followerService, times(1)).unfollowUser(userId, followingId);
	}

	// Get all followers
	// Get all foolowers by user id
	// Get all followers by following id
}
