package com.sparta.anonymousboard.domain.post.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class PostRequestDto {

    @Size(max = 500)
    private String title;

    @Size(max = 5000)
    private String content;

}
