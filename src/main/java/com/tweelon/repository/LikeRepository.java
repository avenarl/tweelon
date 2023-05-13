/**************************************************************************************
 * LikeRepository.java 																																*
 *																																										*
 * Author: avenarl																																		*
 * Created on: 05-14-23																																*
 *																																										*
 * Description: A LikeRepository interface for handling data access and database 			*
 *              operations related to Like entity. Extends JpaRepository to leverage 	*
 *              Spring Data JPA's built-in methods.																		*
 *																																										*
 **************************************************************************************/

package com.tweelon.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tweelon.model.Like;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long>{

	// SELECT * FROM likes WHERE like_id = ?1 and user_id = ?2;
	Optional<Like> findLikeByIdAndUserId(Like likeId, Long userId);

	// SELECT * FROM likes WHERE user_id = ?1;
	List <Like> findLikesByUserId(Long userId);

}
