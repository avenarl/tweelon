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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class LikeServiceTest {

    @Mock
    private LikeRepository likeRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private LikeServiceImpl likeServiceImpl;

    @Test
    public void testLikeTweet() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        Like like = new Like();
        like.setUserId(user);

        given(likeRepository.save(like)).willReturn(like);
        Like returnedLike = likeServiceImpl.likeTweet(like, userId);

        assertEquals(like, returnedLike);
        verify(likeRepository, times(1)).save(like);
    }

    //	Get like by Id
    @Test
    void testGetLikeById() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        Like like = new Like();
        like.setId(1L);
        like.setUserId(user);

        given(likeRepository.findLikeByIdAndUserId(like.getId(), userId)).willReturn(Optional.of(like));
        Like returnedLike = likeServiceImpl.getLikeById(like.getId(), userId);

        assertEquals(like.getId(), returnedLike.getId());
        assertEquals(userId, returnedLike.getUserId().getId());

        verify(likeRepository).findLikeByIdAndUserId(like.getId(), userId);
    }

    //	Get all likes
    @Test
    void testGetAllLikes() {
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
    @Test
    void testGetLikesByUserId() {
        List<Like> likeList = new ArrayList<>();

        Long userId = 1L;
        User user1 = new User();
        user1.setId(userId);

        Like like1 = new Like();
        like1.setUserId(user1);
        likeList.add(like1);

        given(likeRepository.findLikesByUserId(1L)).willReturn(likeList);

        List<Like> returnedLike = likeServiceImpl.getLikesByUserId(1L);
        assertNotNull(returnedLike);
        assertEquals(1, returnedLike.size());
        assertEquals(like1, returnedLike.get(0));

        verify(likeRepository).findLikesByUserId(1L);
    }

    //	Remove like
    @Test
    public void testUnlikeTweet() {
        Long likeId = 1L;

        given(likeRepository.existsById(likeId)).willReturn(true);
        doNothing().when(likeRepository).deleteById(likeId);

        likeServiceImpl.unlikeTweet(likeId);

        verify(likeRepository, times(1)).existsById(likeId);
        verify(likeRepository, times(1)).deleteById(likeId);
    }
}
