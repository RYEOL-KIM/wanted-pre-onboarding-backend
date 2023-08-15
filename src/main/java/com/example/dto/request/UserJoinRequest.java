package com.example.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@NoArgsConstructor
@Getter
public class UserJoinRequest {
    @Pattern(regexp = ".*@.*", message = "이메일 주소에는 @이 포함되어야 합니다.")
    private String email;

    @Pattern(regexp = ".{8,}", message = "비밀번호는 최소 8자 이상이어야 합니다.")
    private String password;

    @Builder
    public UserJoinRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
