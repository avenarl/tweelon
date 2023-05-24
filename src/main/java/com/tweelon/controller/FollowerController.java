/*
 * FollowerController.java
 *
 * Author: avenarl
 * Created on: 05-12-23
 *
 * Description: A REST Controller for handling Follower-related operations in the
 * 						 	Tweelon Application. This class maps endpoints for creating,
 * 						 	deleting, and fetching tweets.
 *
 **/

package com.tweelon.controller;

import java.util.List;

import com.tweelon.model.Follower;
import com.tweelon.service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/follower")
public class FollowerController {

	@Autowired
	public FollowerService followerService;

	// Create a follower
	@PostMapping("/{userId}/{followingId}")
	public Follower followUser(@PathVariable Long userId, @PathVariable Long followingId){
		return followerService.followUser(userId, followingId);
	}

	// Delete a follower
	@DeleteMapping("/{userId}/{followingId}")
	public void unfollowUser(@PathVariable Long userId, @PathVariable Long followingId){
		followerService.unfollowUser(userId, followingId);
	}

	// Fetch all followers
	@GetMapping("/followers")
	public List<Follower> getAllFollowers(){
		return followerService.getAllFollowers();
	}

	// Fetch all followers by user id
	@GetMapping("/followers/user/{userId}")
	public List<Follower> getFollowersByUserId(@PathVariable Long userId){
		return followerService.getFollowersByUserId(userId);
	}

	// Fetch all followers by following id
	@GetMapping("/following/{followingId}")
	public List<Follower> getFollowingByFollowingId(@PathVariable Long followingId){
		return followerService.getFollowingByFollowingId(followingId);
	}

}






























