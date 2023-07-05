/*
 * LikeController.java
 *
 * Author: avenarl
 * Created on: 05-14-23
 *
 * Description: A REST Controller for handling Like-related operations in the
 *              Tweelon Application. This class maps endpoints for creating,
 *              deleting, and fetching likes.
 *
 **/

package com.tweelon.controller;

import java.util.List;

import com.tweelon.model.Like;
import com.tweelon.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/like")
public class LikeController {

    @Autowired
    private LikeService likeService;

    // Like a tweet
    @PostMapping("/{userId}/{likeId}")
    public Like likeTweet(@RequestBody Like like, @PathVariable Long userId) {
        return likeService.likeTweet(like, userId);
    }

    // Delete or unlike
    @DeleteMapping("/{likeId}")
    public void unlikeTweet(@PathVariable Long likeId) {
        likeService.unlikeTweet(likeId);
    }

    // Fetch likes by id
    @GetMapping("/likes/{likeId}/{userId}")
    public Like getLikeById(@PathVariable Long likeId, @PathVariable Long userId) {
        return likeService.getLikeById(likeId, userId);
    }

    // Fetch all likes
    @GetMapping("/likes")
    public List<Like> getAllLikes() {
        return likeService.getAllLikes();
    }

    // Fetch likes by user id
    @GetMapping("/likes/user/{userId}")
    public List<Like> getLikesByUserId(@PathVariable Long userId) {
        return likeService.getLikesByUserId(userId);
    }

}

