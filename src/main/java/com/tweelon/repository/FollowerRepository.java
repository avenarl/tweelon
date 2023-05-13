/**************************************************************************************
 * FollowerRepository.java 																														*
 *																																										*
 * Author: avenarl																																		*
 * Created on: 05-14-23																																*
 *																																										*
 * Description: A FollowerRepository interface for handling data access and database 	*
 * 				 		 operations related to Follower entity. Extends JpaRepository to 				*
 * 				 		 leverage Spring Data JPA's built-in methods.														*
 * 																																										*
 **************************************************************************************/

package com.tweelon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tweelon.model.Follower;

import graphql.com.google.common.base.Optional;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long>{ // id = Long
  
	// SELECT * FROM followers WHERE user_id = ?1 AND following_id = ?2;
	Optional<Follower> findByUserIdAndFollowingId(Long userId, Long followingId);

	// SELECT * FROM followers WHERE user_id = ?1;
	List<Follower> findByUserId(Long userId);

	// SELCT * FROM followers WHERE user_id = ?1;
	List<Follower> findByFollowingId(Long userId);

}
