/*
 * RetweetController.java
 *
 * Author: avenarl
 * Created on: 05-14-23
 *
 * Description: A REST Controller for handling Retweet-related operations in the
 *              Tweelon Application. This class maps endpoints for creating,
 *              deleting, and fetching retweets.
 *
 **/

package com.tweelon.controller;

import java.util.List;

import com.tweelon.model.Retweet;
import com.tweelon.service.RetweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/retweet")
public class RetweetController {

    @Autowired
    private RetweetService retweetService;

    // Create a retweet
    @PostMapping("/{userId}/{tweetId}")
    public Retweet createRetweet(@PathVariable Long userId, @PathVariable Long tweetId) {
        return retweetService.createRetweet(userId, tweetId);
    }

    // Delete a retweet
    @DeleteMapping("/{retweetId}")
    public void deleteRetweet(@PathVariable Long retweetId) {
        retweetService.deleteRetweet(retweetId);
    }

    // Fetch all retweets
    @GetMapping("/{tweetId}")
    public List<Retweet> getAllRetweets() {
        return retweetService.getAllRetweets();
    }

    // Fetch all retweet by user id
    @GetMapping("/retweets/user/{userId}")
    public List<Retweet> getRetweetsByUserId(@PathVariable Long userId) {
        return retweetService.getRetweetsByUserId(userId);
    }

    // Fetch all retweet by tweet id
    @GetMapping("/retweets/tweet/{tweetId}")
    public List<Retweet> getRetweetsByTweetId(@PathVariable Long tweetId) {
        return retweetService.getRetweetsByUserId(tweetId);
    }

}

