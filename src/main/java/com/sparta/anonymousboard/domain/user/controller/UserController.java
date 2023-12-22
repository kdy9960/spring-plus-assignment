package com.sparta.anonymousboard.domain.user.controller;

import com.sparta.anonymousboard.domain.user.dto.LoginRequestDto;
import com.sparta.anonymousboard.domain.user.dto.SignupRequestDto;
import com.sparta.anonymousboard.domain.user.service.UserService;
import com.sparta.anonymousboard.global.common.CommonResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v0/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponseDto> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CommonResponseDto(HttpStatus.CREATED.value(), "회원가입이 완료되었습니다."));

    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        userService.login(loginRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(new CommonResponseDto(HttpStatus.OK.value(), "로그인 되었습니다."));

    }


}
