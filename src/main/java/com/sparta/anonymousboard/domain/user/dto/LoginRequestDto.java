package com.sparta.anonymousboard.domain.user.dto;

import lombok.Getter;

@Getter
public class LoginRequestDto {
    private String nickname;
    private String password;
}
