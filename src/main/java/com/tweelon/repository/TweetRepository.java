/**************************************************************************************
 * TweetRepository.java 																															*
 *																																										*
 * Author: avenarl																																		*
 * Created on: 05-14-23																																*
 *																																										*
 * Description: A TweetRepository interface for handling data access and database 		*
 * 						  operations related to Tweet entity. Extends JpaRepository to 					*
 * 						  leverage Spring Data JPA's built-in methods.													*
 *																																										*
 **************************************************************************************/

package com.tweelon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tweelon.model.Tweet;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
    List<Tweet> findByUserId(Long userId);
}
