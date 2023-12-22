package com.sparta.anonymousboard.domain.post.service;

import com.sparta.anonymousboard.domain.post.dto.PostRequestDto;
import com.sparta.anonymousboard.domain.post.dto.PostResponseDto;
import com.sparta.anonymousboard.domain.post.entity.Post;
import com.sparta.anonymousboard.domain.post.repository.PostRepository;
import com.sparta.anonymousboard.domain.user.entity.User;
import com.sparta.anonymousboard.domain.user.repository.UserRepository;
import com.sparta.anonymousboard.global.exception.CustomException;
import com.sparta.anonymousboard.global.exception.ExceptionResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostResponseDto createPost(PostRequestDto postRequestDto, Long id) {
        User user = userRepository.findById(id).orElseThrow(
                ()-> new CustomException(ExceptionResponseCode.NOT_FOUND_USER));

        Post post = new Post();
        postRepository.save(post);
        return new PostResponseDto(post)
    }

    public PostResponseDto getPost(Long postId) {
    }

    public Post getPostList() {
    }

    public void updatePost(Long postId, PostRequestDto postRequestDto, User user) {
    }

    public void deletePost(Long postId, User user) {
    }
}
