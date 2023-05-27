package com.tweelon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tweelon.model.Like;
import com.tweelon.service.LikeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LikeControllerTest {
		@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LikeService likeService;

    private Like like;

  	@BeforeEach
    void setUp() {
        like = new Like();
        like.setId(1L);
        // Set other properties of the like object
    }

		@Test
    void testLikeTweet() throws Exception {
        given(likeService.likeTweet(any(Like.class), anyLong())).willReturn(like);

        mockMvc.perform(post("/api/v1/like/{userId}/{likeId}", 1, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(like)))
                .andExpect(status().isOk());
    }
}


