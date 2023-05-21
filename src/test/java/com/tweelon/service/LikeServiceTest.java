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
	//	Get likes by user id
	
}
