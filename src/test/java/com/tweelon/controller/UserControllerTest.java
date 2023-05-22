package com.tweelon.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.tweelon.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tweelon.model.User;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
    "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration"
})
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc; // HTTP requests

	@MockBean // mock instance
	private UserService userService;

	// Get all users
	// Get single user
	
	// Create a user
	@Test
	public void testCreateUser() throws Exception {

		User user = new User();
		user.setId(1L);
    user.setUsername("testuser");
    user.setEmail("testuser@gmail.com");
    user.setPassword("testuser@123");
    user.setDisplayName("Test User");
    user.setBio("My name is Test User. I'm a tester.");
    when(userService.createUser(any(User.class))).thenReturn(user);

    mockMvc.perform(post("/api/v1/user/")
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(user)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.username", is("testuser")));

    verify(userService, times(1)).createUser(any(User.class));
	}

	// Update a user
	@Test
	public void testUpdateUser() throws Exception {

		User user = new User();
		user.setId(1L);
    user.setUsername("testuser");
    user.setEmail("testuser@gmail.com");
    user.setPassword("testuser@123");
    user.setDisplayName("Test User");
    user.setBio("My name is Test User. I'm a tester.");
    when(userService.createUser(any(User.class))).thenReturn(user);

    when(userService.updateUser(any(User.class) , any(Long.class))).thenReturn(user);

    mockMvc.perform(put("/api/v1/user/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(user)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.username", is("testuser")));
    verify(userService, times(1)).updateUser(any(User.class), any(Long.class));
	}

	// Delete a user
	@Test
	public void testDeleteUser() throws Exception {
    doNothing().when(userService).deleteUser(1L);

    mockMvc.perform(delete("/api/v1/user/1"))
        .andExpect(status().isOk());

    verify(userService, times(1)).deleteUser(1L);
	}
}
