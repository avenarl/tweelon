package com.tweelon.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tweelon.repository.FollowerRepository;
import com.tweelon.model.Follower;
import com.tweelon.service.impl.FollowerServiceImpl;

import com.tweelon.model.User;
import com.tweelon.repository.UserRepository;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class FollowerServiceTest {

	@Mock
	private FollowerRepository followerRepository;	

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private FollowerServiceImpl followerServiceImpl;
		
	// Follower user
	@Test
	public void testFollowUser() {
		Long userId = 1L;
    Long followingId = 2L;
    
    User user = new User();
    User followingUser = new User();
    
    Follower follower = new Follower();
    follower.setUserId(user);
    follower.setFollowingId(followingUser);

    given(userRepository.findById(userId)).willReturn(Optional.of(user));
    given(userRepository.findById(followingId)).willReturn(Optional.of(followingUser));
    given(followerRepository.save(any(Follower.class))).willReturn(follower);

    Follower savedFollower = followerServiceImpl.followUser(userId, followingId);

    assertEquals(savedFollower, follower);
    assertEquals(savedFollower.getUserId(), user);
    assertEquals(savedFollower.getFollowingId(), followingUser);
    
    verify(userRepository, times(1)).findById(userId);
    verify(userRepository, times(1)).findById(followingId);
    verify(followerRepository, times(1)).save(any(Follower.class));
	}

	// Unfollow a user
	@Test
	public void testUnfollowUserNotExistingFollower() {
    Long userId = 1L;
    Long followingId = 2L;
    
    given(followerRepository.findByUserIdAndFollowingId(userId, followingId)).willReturn(Optional.empty());

    assertThrows(RuntimeException.class, () -> {
        followerServiceImpl.unfollowUser(userId, followingId);
    });

    verify(followerRepository).findByUserIdAndFollowingId(userId, followingId);
    verify(followerRepository, times(0)).delete(any(Follower.class));
	}

	// Get all followers
	@Test
	public void testGetAllFollowers() {
    List<Follower> followers = new ArrayList<>();
    followers.add(new Follower());
    followers.add(new Follower());

    given(followerRepository.findAll()).willReturn(followers);

    List<Follower> result = followerServiceImpl.getAllFollowers();

    assertEquals(2, result.size());
    verify(followerRepository).findAll();
	}

	// Get following by following id
	@Test
	public void testGetFollowingByFollowingId() {
    Long followingId = 1L;
    List<Follower> followers = new ArrayList<>();
    followers.add(new Follower());
    followers.add(new Follower());

    given(followerRepository.findByFollowingId(followingId)).willReturn(followers);

    List<Follower> result = followerServiceImpl.getFollowingByFollowingId(followingId);

    assertEquals(2, result.size());
    verify(followerRepository).findByFollowingId(followingId);
	}

	// Get followers by user id
	@Test
	public void testGetFollowersByUserId() {
    Long userId = 1L;
    List<Follower> followers = new ArrayList<>();
    followers.add(new Follower());
    followers.add(new Follower());

    given(followerRepository.findByUserId(userId)).willReturn(followers);

    List<Follower> result = followerServiceImpl.getFollowersByUserId(userId);

    assertEquals(2, result.size());
    verify(followerRepository).findByUserId(userId);
	}
}
