/*
 * LikeServiceImpl.java
 *
 * Author: avenarl
 * Created on: 05-14-23
 *
 * Description: This class provides the implementation of the LikeService interface.
 *              It handles the business logic for managing likes in the Tweelon
 *              application. It provides methods for creating, retrieving, updating,
 *              and deleting likes, as well as retrieving likes by user or tweet.
 *
 **/

package com.tweelon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweelon.model.Like;
import com.tweelon.repository.LikeRepository;
import com.tweelon.repository.UserRepository;
import com.tweelon.service.LikeService;

@Service
public class LikeServiceImpl implements LikeService {

	@Autowired
	public LikeRepository likeRepository;

	@Autowired
	public UserRepository userRepository;

	@Override
	public Like likeTweet(Like like){
		return likeRepository.save(like);
	}

	@Override
	public Like getLikeById(Long likeId, Long userId){
		// Get all likes by its ID & user ID and return
		return likeRepository.findLikeByIdAndUserId(likeId, userId).orElseThrow(() -> new RuntimeException("Like not found."));
	}

  @Override
	public void unlikeTweet(Long id){
		if (!likeRepository.existsById(id)) {
			throw new RuntimeException("Like not found.");
		}
		// Remove like
		likeRepository.deleteById(id);
  }

	@Override
	public List<Like> getAllLikes(){
		// Get all likes and return
	  return likeRepository.findAll();		
	}

	@Override
	public List<Like> getLikesByUserId(Long userId){
		// Get all likes by user ID and return
		List<Like> likesByUserId = likeRepository.findLikesByUserId(userId);
		return likesByUserId;
	}

}

