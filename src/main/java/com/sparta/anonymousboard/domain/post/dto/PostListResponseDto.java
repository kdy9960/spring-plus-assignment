package com.sparta.anonymousboard.domain.post.dto;

import com.sparta.anonymousboard.domain.post.entity.Post;
import java.time.LocalDateTime;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class PostListResponseDto {
    private String title;

    private String nickname;

    private LocalDateTime createDate;

    public PostListResponseDto(Post post) {
        this.title = post.getTitle();
        this.nickname = post.getUser().getNickname();
        this.createDate = post.getCreateDate();
    }
}
