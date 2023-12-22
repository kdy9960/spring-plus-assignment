package com.sparta.anonymousboard.domain.review.service;

import com.sparta.anonymousboard.domain.post.entity.Post;
import com.sparta.anonymousboard.domain.post.repository.PostRepository;
import com.sparta.anonymousboard.domain.review.dto.ReviewRequestDto;
import com.sparta.anonymousboard.domain.review.dto.ReviewResponseDto;
import com.sparta.anonymousboard.domain.review.entity.Review;
import com.sparta.anonymousboard.domain.review.repository.ReviewRepository;
import com.sparta.anonymousboard.domain.user.entity.User;
import com.sparta.anonymousboard.domain.user.repository.UserRepository;
import com.sparta.anonymousboard.global.exception.CustomException;
import com.sparta.anonymousboard.global.exception.ExceptionResponseCode;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ReviewRepository reviewRepository;



    public void creatReview(ReviewRequestDto requestDto, Long postId, Long userId) {

        User user = userRepository.findById(postId).orElseThrow(
                () -> new CustomException(ExceptionResponseCode.NOT_FOUND_USER)
        );

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new CustomException(ExceptionResponseCode.NOT_FOUND_POST)
        );

        if (post.isDeleted()) {
            throw new CustomException(ExceptionResponseCode.BAD_REQUEST_NOT_MATCH_POST);
        }


        Review review = new Review(requestDto, post.getId(), user.getId());

        reviewRepository.save(review);
    }

    public ReviewResponseDto getPostReviewDto(Long postId, Long reviewId) {

        if(!postRepository.existsById(postId)) {
            throw new CustomException(ExceptionResponseCode.NOT_FOUND_POST);
        }

        Review review = findAndGetReview(reviewId);

        return new ReviewResponseDto(review);
    }

    public List<ReviewResponseDto> getPostReviewList(Long postId) {

        if(!postRepository.existsById(postId)) {
            throw new CustomException(ExceptionResponseCode.NOT_FOUND_POST);
        }

        List<ReviewResponseDto> reviewResponseDtoList = reviewRepository.findAllByPostId(postId).stream().map(ReviewResponseDto::new).toList();

        return reviewResponseDtoList;
    }

    @Transactional
    public void updateReview(Long reviewId, ReviewRequestDto reviewRequestDto, User user) {

        Review review = getUserReview(reviewId, user);

        review.setContent(reviewRequestDto.getContent());
    }

    public void deleteReview(Long reviewId, User user) {

        Review review = getUserReview(reviewId, user);

        reviewRepository.delete(review);
    }


    private Review getUserReview(Long reviewId, User user) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(ExceptionResponseCode.NOT_FOUND_REVIEW));

        if (!user.getId().equals(review.getUser().getId())) {
            throw new CustomException(ExceptionResponseCode.FORBIDDEN_YOUR_NOT_COME_IN);
        }

        return review;
    }

    public Review findAndGetReview(Long reviewId) {

        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(ExceptionResponseCode.NOT_FOUND_USER));
    }

}