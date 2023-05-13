/**************************************************************************************
 * FollowerServiceImpl.java																														*
 *																																										*
 * Author: avenarl																																		*
 * Created on: 05-14-23																																*
 *																																										*
 * Description: This class provides the implementation of the FollowerService 				*
 * 					 	  interface.It handles the business logic for managing followers in 		*
 * 					 	  the Tweelon application.It provides methods for adding and removing 	*
 * 					 	  followers, and retrieving followers of a user or users followed by a 	*
 * 					 	  user.																																	*
 *																																										*
 **************************************************************************************/

package com.tweelon.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweelon.model.Follower;
import com.tweelon.model.User;
import com.tweelon.repository.FollowerRepository;
import com.tweelon.repository.UserRepository;
import com.tweelon.service.FollowerService;

@Service
public class FollowerServiceImpl implements FollowerService{

	
  @Autowired
	private UserRepository userRepository;

	@Autowired
	private FollowerRepository followerRepository;

	@Override
	public Follower followUser(Long userId, Long followingId){
		// Find User with userId
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));

		// Find User with followingId
		User followingUser = userRepository.findById(followingId).orElseThrow(() -> new RuntimeException("Following user not found."));

		// Creates a new follower object
		Follower follower = new Follower();
		
		// Set follower's user to user and followingUser to User
		follower.setUserId(user);
		follower.setFollowingId(followingUser);

		// Save and return the follower
		return followerRepository.save(follower);
	}

	@Override
	public void unfollowUser(Long userId, Long followingId){
		// A method in Follower Repository 
		Optional<Follower> followerRecord = followerRepository.findByUserIdAndFollowingId(userId, followingId);

		if (followerRecord.isPresent()){
			followerRepository.delete(followerRecord.get());
		} else {
			throw new RuntimeException("Follower record not found.");
		}
	}

	@Override
	public List<Follower> getAllFollowers(){
		// Get all records and return
		List<Follower> allFollowers = followerRepository.findAll();
		return allFollowers;	
	}

	@Override
	public List<Follower> getFollowersByUserId(Long userId){
		// Get all followers by user ID and return
		List<Follower> userFollowers = followerRepository.findByUserId(userId);
		return userFollowers;
	}

	@Override
	public List<Follower> getFollowingByFollowingId(Long followerId){
		// Get all following of user by user ID and return
		List<Follower> userFollowing = followerRepository.findByFollowingId(followerId);
		return userFollowing;
	}
}
