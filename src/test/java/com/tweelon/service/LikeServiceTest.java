package com.tweelon.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tweelon.model.Like;
import com.tweelon.model.User;
import com.tweelon.repository.LikeRepository;
import com.tweelon.repository.UserRepository;
import com.tweelon.service.impl.LikeServiceImpl;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class LikeServiceTest {

	@Mock
	private LikeRepository likeRepository;

	@Mock
	private UserRepository userReository;

	@InjectMocks
	private LikeServiceImpl likeServiceImpl;

	//	Get like by Id
	@Test
	void testGetLikeById(){
		Long userId = 1L;
    User user = new User();
    user.setId(userId);

    Like like = new Like();
    like.setId(1L);
    like.setUserId(user);

    given(likeRepository.findLikeByIdAndUserId(like.getId(), userId)).willReturn(Optional.of(like));

    // When
    Like returnedLike = likeServiceImpl.getLikeById(like.getId(), userId);

    // Then
    assertEquals(like.getId(), returnedLike.getId());
    assertEquals(userId, returnedLike.getUserId().getId());

    // Verify
    verify(likeRepository).findLikeByIdAndUserId(like.getId(), userId);
	}
	//	Remove like
	//	Get all likes
	@Test 
	void testGetAllLikes(){
		// prepare sample data
		List<Like> likeList = new ArrayList<>();
		Long userId1 = 1L;
		User user1 = new User();
		user1.setId(userId1);

		Like like1 = new Like();
		like1.setId(1L);
		like1.setUserId(user1);
		likeList.add(like1);

		Long userId2 = 2L;
		User user2 = new User();
		user2.setId(userId2);

		Like like2 = new Like();
		like2.setId(2L);
		like2.setUserId(user2);
		likeList.add(like2);

		given(likeRepository.findAll()).willReturn(likeList);
		
		List<Like> returnedLikeList = likeServiceImpl.getAllLikes();

		assertEquals(returnedLikeList.size(), 2);
		assertEquals(returnedLikeList.get(0).getId(), 1L);
		assertEquals(returnedLikeList.get(1).getId(), 2L);

		verify(likeRepository).findAll();
	}
	//	Get likes by user id
	
















}
