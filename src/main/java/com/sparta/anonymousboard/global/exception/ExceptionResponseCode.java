package com.sparta.anonymousboard.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionResponseCode {

    // BAD_REQUEST 400
    BAD_REQUEST_NOT_MATCH_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다"),

    BAD_REQUEST_NOT_MATCH_POST(HttpStatus.BAD_REQUEST, "포스트가 일치하지 않습니다"),

    // FORBIDDEN 403
    FORBIDDEN_YOUR_NOT_COME_IN(HttpStatus.FORBIDDEN, "권한이 없습니다"),

    // NOT_FOUND 404
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "해당 유저는 존재하지 않습니다."),

    NOT_FOUND_POST(HttpStatus.NOT_FOUND, "포스트가 존재하지 않습니다."),

    NOT_FOUND_REVIEW(HttpStatus.NOT_FOUND, "리뷰가 존재하지 않습니다."),

    // CONFLICT 409
    CONFLICT_USER_NICKNAME(HttpStatus.CONFLICT, "사용자 닉네임이 이미 사용 중입니다");

    private final HttpStatus httpStatus;
    private final String message;

}
