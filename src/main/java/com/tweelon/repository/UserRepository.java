/**************************************************************************************
 * UserRepository.java 																																*
 *																																										*
 * Author: avenarl																																		*
 * Created on: 05-14-23																																*
 *																																										*
 * Description: A UserRepository interface for handling data access and database 			*
 * 						  operations related to User entity. Extends JpaRepository to leverage 	*
 * 						  Spring Data JPA's built-in methods.																		*
 *																																										*
 **************************************************************************************/

package com.tweelon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tweelon.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{ // id = Long

}
