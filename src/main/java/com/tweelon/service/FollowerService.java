/**************************************************************************************
 * FollowerService.java																																*
 *																																										*
 * Author: avenarl																																		*
 * Created on: 05-14-23																															  *
 *																																										*
 * Description: This interface defines the business logic for managing followers in 	*
 * 						  the Tweelon Application.It provides methods for following, 						*
 * 						  unfollowing, and retrieving followers and following lists based on 		*
 * 						  user IDs.																															*
 * 																																										*
 **************************************************************************************/

package com.tweelon.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tweelon.model.Follower;

@Service
public interface FollowerService {

    Follower followUser(Long userId, Long followingId); // Follow a User by its user ID and the assoicated following ID.

    void unfollowUser(Long userId, Long followingId); // Unfollow a User by its user ID and the associated following ID.

    List<Follower> getAllFollowers(); // Retrieves all Followers in the system.

    List<Follower> getFollowersByUserId(Long userId); // Retrieves Followers by its user ID.

    List<Follower> getFollowingByFollowingId(Long followingId);// Retrieves Following by its user ID.

}
