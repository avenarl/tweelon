/*
 * LikeService.java
 *
 * Author: avenarl
 * Created on: 05-14-23
 *
 * Description: This interface defines the business logic for managing likes in the
 *              Tweelon Application. It provides methods for creating, retrieving,
 *              updating and deleting likes, as well as other like-related
 *              operations.
 *
 **/

package com.tweelon.service;

import java.util.List;

import com.tweelon.model.Like;

public interface LikeService {

	Like getLikeById(Long likeId, Long userId); // Retrievs a Like by its ID and the associated user ID.
	void unLike(Long id); // Deletes a Like by its ID.
	List<Like> getAllLikes(); // Retrieves all Likes in the system.
	List<Like> getLikesByUserId(Long userId); // Retrieves all Likes associated with a specific user ID.
	//
}	

