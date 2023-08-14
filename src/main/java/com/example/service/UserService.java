package com.example.service;

import com.example.entity.User;
import com.example.exception.AppException;
import com.example.exception.ErrorCode;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

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
        //

        //

        //

        return "SUCCESS";
    }
}
