package com.sparta.anonymousboard.domain.review.controller;

import com.sparta.anonymousboard.domain.review.dto.ReviewListResponseDto;
import com.sparta.anonymousboard.domain.review.dto.ReviewRequestDto;
import com.sparta.anonymousboard.domain.review.dto.ReviewResponseDto;
import com.sparta.anonymousboard.domain.review.service.ReviewService;
import com.sparta.anonymousboard.domain.security.userDetails.UserDetailsImpl;
import com.sparta.anonymousboard.global.common.CommonResponseDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @PostMapping("/posts/{postId}/review") // 리뷰 작성
    public ResponseEntity<CommonResponseDto> postReview(
            @RequestBody @Valid ReviewRequestDto reviewRequestDto, @PathVariable Long postId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        reviewService.creatReview(reviewRequestDto, postId, userDetails.getUser().getId());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CommonResponseDto(HttpStatus.CREATED.value(), "리뷰가 작성되었습니다"));
    }

    @GetMapping("/posts/{postId}/reviews/{reviewId}") // 포스트 리뷰 단일 조회
    public ResponseEntity<ReviewResponseDto> getPostReview(@PathVariable("postId") Long storeId,
            @PathVariable("reviewId") Long reviewId) {

        ReviewResponseDto reviewResponseDto = reviewService.getPostReviewDto(storeId, reviewId);

        return ResponseEntity.ok().body(reviewResponseDto);

    }

    @GetMapping("/posts/{postId}/reviews") // 포스트 리뷰 목록 조회
    public ResponseEntity<ReviewListResponseDto> getPostReviewList(@PathVariable("postId") Long postId) {

        ReviewListResponseDto reviewListResponseDto = new ReviewListResponseDto(
                reviewService.getPostReviewList(postId));

        return ResponseEntity.ok().body(reviewListResponseDto);
    }

    @PatchMapping("/reviews/{reviewId}") // 리뷰 수정
    public ResponseEntity<CommonResponseDto> patchReview(@PathVariable("reviewId") Long reviewId,
            @RequestBody @Valid ReviewRequestDto reviewRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        reviewService.updateReview(reviewId, reviewRequestDto, userDetails.getUser());
        return ResponseEntity.ok()
                .body(new CommonResponseDto(HttpStatus.BAD_REQUEST.value(), "리뷰가 수정되었습니다."));
    }

    @DeleteMapping("/reviews/{reviewId}") // 리뷰 삭제
    public ResponseEntity<CommonResponseDto> deleteReview(@PathVariable("reviewId") Long reviewId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        reviewService.deleteReview(reviewId, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .body(new CommonResponseDto(HttpStatus.CREATED.value(), "리뷰가 삭제되었습니다."));
    }
}