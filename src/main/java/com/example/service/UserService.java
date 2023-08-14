package com.example.service;

import com.example.entity.User;
import com.example.exception.AppException;
import com.example.exception.ErrorCode;
import com.example.repository.UserRepository;
import com.example.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.token.secret}")
    private String key;

    private Long expiredTimeMs = 1000 * 60 * 60l;

    public String join(String email, String password) {

        // 이메일 중복 체크
        userRepository.findByEmail(email)
                .ifPresent(user -> {
                    throw new AppException(ErrorCode.EMAIL_DUPLICATED, "이미 존재하는 이메일 입니다");
                });

        // 저장
        User user = User.builder()
                .email(email)
                .password(encoder.encode(password))
                .build();
        userRepository.save(user);

        return "SUCCESS";
    }

    public String login(String email, String password) {
        // email 없음
        User selectedUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.EMAIL_NOT_FOUND, "등록되지 않은 이메일 입니다"));

        // password 틀림
        if(!encoder.matches(password, selectedUser.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD, "유효하지 않은 비밀번호 입니다");
        }

        // 토큰 발행
        String token = JwtTokenUtil.createToken(selectedUser.getEmail(), key, expiredTimeMs);

        return token;
    }
}
