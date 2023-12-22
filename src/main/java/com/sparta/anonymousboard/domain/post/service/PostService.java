package com.sparta.anonymousboard.domain.post.service;

import com.sparta.anonymousboard.domain.post.dto.PostRequestDto;
import com.sparta.anonymousboard.domain.post.dto.PostResponseDto;
import com.sparta.anonymousboard.domain.post.entity.Post;
import com.sparta.anonymousboard.domain.post.repository.PostRepository;
import com.sparta.anonymousboard.domain.user.entity.User;
import com.sparta.anonymousboard.domain.user.repository.UserRepository;
import com.sparta.anonymousboard.global.common.CommonResponse;
import com.sparta.anonymousboard.global.common.CommonResponseCode;
import com.sparta.anonymousboard.global.exception.CustomException;
import com.sparta.anonymousboard.global.exception.ExceptionResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public void createPost(PostRequestDto postRequestDto, Long id) {
        User user = userRepository.findById(id).orElseThrow(
                ()-> new CustomException(ExceptionResponseCode.NOT_FOUND_USER));


        Post post = new Post(postRequestDto, );

        postRepository.save(post);
    }

    public PostResponseDto getPost(Long postId) {
        Post post = find
    }

    public Post getPostList() {
    }

    public void updatePost(Long postId, PostRequestDto postRequestDto, User user) {
    }

    public void deletePost(Long postId, User user) {
        Post post =

        postRepository.delete(post);
    }
}
