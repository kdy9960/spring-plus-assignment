package com.sparta.anonymousboard.domain.review.dto;

import com.sparta.anonymousboard.domain.review.entity.Review;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewResponseDto {
    private String nickname;
    private String contents;
    private String createdAt;
    public ReviewResponseDto(Review review) {
        this.nickname = review.getUser().getNickname();
        this.contents = review.getContent();
        this.createdAt = review.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}