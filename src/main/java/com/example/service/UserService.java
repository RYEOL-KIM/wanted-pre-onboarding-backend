package com.example.service;

import com.example.entity.User;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public String join(String email, String password) {

        // 이메일 중복 체크
        userRepository.findByEmail(email)
                .ifPresent(user -> {
                    throw new RuntimeException("이미 존재하는 이메일 입니다");
                });

        // 저장
        User user = User.builder()
                .email(email)
                .password(password)
                .build();
        userRepository.save(user);

        return "SUCCESS";
    }
}
