package com.sparta.anonymousboard.global.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonResponseCode {

    // 회원가입 리스폰스
    User_CREATE(HttpStatus.CREATED, "회원가입이 완료되었습니다.."),
    User_NICKNAME_OK(HttpStatus.OK, "사용 가능한 이름입니다."),

    // 포스트 리스폰스
    POST_CREATE(HttpStatus.OK, "POST가 생성되었습니다."),;


    private final HttpStatus httpStatus;
    private final String message;
}
