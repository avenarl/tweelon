/**************************************************************************************
 * RetweetRepository.java 																														*
 *																																										*
 * Author: avenarl																																		*
 * Created on: 05-14-23																																*
 *																																										*
 * Description: A RetweetRepository interface for handling data access and database 	*
 *  						operations related to Retweet entity. Extends JpaRepository to 				*
 *  						leverage Spring Data JPA's built-in methods.													*
 *																																										*																															
 **************************************************************************************/

package com.tweelon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tweelon.model.Retweet;

@Repository
public interface RetweetRepository extends JpaRepository<Retweet, Long>{

	// SELECT * FROM retweets WHERE user_id = ?1 AND tweet_id = ?2;
	Retweet findRetweetsByIdAndUserId(Long userId, Long tweetId);

	// SELECT * FROM retweets WHERE user_id = ?1;
  List<Retweet> findByUserId(Long userId);

	// SELECT * FROM retweets WHERE tweet_id = ?2; 
	List<Retweet> findByTweetId(Long tweetId);

}	
