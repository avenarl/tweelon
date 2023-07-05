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

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;

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

    @Test
    public void testLoginUser() throws Exception {
        User user1 = new User();
        user1.setUsername("username");
        user1.setPassword("userPassword");

        when(userService.loginUser(any(User.class))).thenReturn(user1);

        mockMvc.perform(post("/api/v1/user")
                        .content(new ObjectMapper().writeValueAsString(user1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testRegisterUser() throws Exception {
        User user = new User();
        user.setUsername("test");
        user.setEmail("test@example.com");

        when(userService.registerUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/v1/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(jsonPath("$.username", is(user.getUsername())))
                .andExpect(jsonPath("$.email", is(user.getEmail())))
                .andExpect(status().isCreated());
    }

    // Get all users
    @Test
    public void testGetAllUsers() throws Exception {
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("testuser1");
        user1.setEmail("testuser1@gmail.com");
        user1.setPassword("testuser@123");
        user1.setDisplayName("Test User 1");
        user1.setBio("My name is Test User 1. I'm a tester.");

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("testuser2");
        user2.setEmail("testuser2@gmail.com");
        user2.setPassword("testuser@123");
        user2.setDisplayName("Test User 2");
        user2.setBio("My name is Test User 2. I'm a tester.");

        List<User> userList = Arrays.asList(user1, user2);
        when(userService.getAllUsers()).thenReturn(userList);

        mockMvc.perform(get("/api/v1/user/users") // send a GET request to given path
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // 200 = OK
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].username", is("testuser1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].username", is("testuser2")));

        verify(userService, times(1)).getAllUsers();
    }

    // Get single user
    @Test
    public void testGetUserById() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setEmail("testuser@gmail.com");
        user.setPassword("testuser@123");
        user.setDisplayName("Test User");
        user.setBio("My name is Test User. I'm a tester.");

        // return test user
        when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/api/v1/user/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.username", is("testuser")))
                .andExpect(jsonPath("$.email", is("testuser@gmail.com")))
                .andExpect(jsonPath("$.password", is("testuser@123")))
                .andExpect(jsonPath("$.displayName", is("Test User")))
                .andExpect(jsonPath("$.bio", is("My name is Test User. I'm a tester.")));

        verify(userService, times(1)).getUserById(1L);
    }

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

        mockMvc.perform(post("/api/v1/user")
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

        when(userService.updateUser(any(User.class), any(Long.class))).thenReturn(user);

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
