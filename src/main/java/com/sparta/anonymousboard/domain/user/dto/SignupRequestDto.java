package com.sparta.anonymousboard.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

    @NotBlank(message = "아이디를 입력해주세요")
    @Size(min = 3)
    @Pattern(regexp = "[a-zA-Z0-9]")
    private String nickname;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Size(min = 4)
    private String password;

    @NotBlank(message = "비밀번호 확인을 입력해주세요")
    private String checkpassword;





}
