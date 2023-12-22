package com.sparta.anonymousboard.domain.review.repository;

import com.sparta.anonymousboard.domain.review.entity.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByPostId(Long postId);
}
