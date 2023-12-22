package com.sparta.anonymousboard.domain.user.service;

import com.sparta.anonymousboard.domain.user.dto.LoginRequestDto;
import com.sparta.anonymousboard.domain.user.dto.SignupRequestDto;
import com.sparta.anonymousboard.domain.user.entity.User;
import com.sparta.anonymousboard.domain.user.entity.UserRoleEnum;
import com.sparta.anonymousboard.domain.user.repository.UserRepository;
import com.sparta.anonymousboard.global.exception.CustomException;
import com.sparta.anonymousboard.global.exception.ExceptionResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(SignupRequestDto signupRequestDto) {
        String nickname = signupRequestDto.getNickname();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        UserRoleEnum userRoleEnum = UserRoleEnum.USER;

        if (userRepository.findByNickname(nickname).isPresent()) {
            throw new CustomException(ExceptionResponseCode.CONFLICT_USER_NICKNAME);
        }
        User user = new User(nickname, password, userRoleEnum);
        userRepository.save(user);
    }

    public void login(LoginRequestDto loginRequestDto) {
        String nickname = loginRequestDto.getNickname();
        String password = loginRequestDto.getPassword();

        User user = userRepository.findByNickname(nickname)
                .orElseThrow(()-> new IllegalArgumentException("등록된 유저가 없습니다."));
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
