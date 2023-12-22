package com.sparta.anonymousboard.domain.post.controller;

import com.sparta.anonymousboard.domain.post.dto.PostListResponseDto;
import com.sparta.anonymousboard.domain.post.dto.PostRequestDto;
import com.sparta.anonymousboard.domain.post.dto.PostResponseDto;
import com.sparta.anonymousboard.domain.post.service.PostService;
import com.sparta.anonymousboard.domain.security.userDetails.UserDetailsImpl;
import com.sparta.anonymousboard.global.common.CommonResponse;
import com.sparta.anonymousboard.global.common.CommonResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v0/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<CommonResponseDto> createPost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        PostResponseDto responseDto = PostService.createPost(postRequestDto, userDetails.getUser().getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(new CommonResponseDto(HttpStatus.CREATED.value(), "포스트가 작성되었습니다."));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long postId){
        PostResponseDto responseDto = postService.getPost(postId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/postList/")
    public ResponseEntity<PostListResponseDto> getPostList() {

        PostListResponseDto postListResponseDto = new PostListResponseDto(
                postService.getPostList());

        return ResponseEntity.ok().body(postListResponseDto);
    }

    @PatchMapping("{postId}")
    public ResponseEntity<CommonResponseDto> patchReview(@PathVariable("postId") Long postId,
            @RequestBody @Valid PostRequestDto postRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.updatePost(postId, postRequestDto, userDetails.getUser());
        return ResponseEntity.ok()
                .body(new CommonResponseDto(HttpStatus.BAD_REQUEST.value(), "리뷰가 수정되었습니다."));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<CommonResponseDto> deletePost(@PathVariable("postId") Long postId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.deletePost(postId, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .body(new CommonResponseDto(HttpStatus.CREATED.value(), "리뷰가 삭제되었습니다."));
    }



}
