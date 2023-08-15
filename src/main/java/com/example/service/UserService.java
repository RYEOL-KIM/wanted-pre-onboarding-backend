package com.example.service;

import com.example.entity.User;
import com.example.exception.AppException;
import com.example.exception.ErrorCode;
import com.example.repository.UserRepository;
import com.example.utils.CustomAuthorityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils authorityUtils;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, CustomAuthorityUtils authorityUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityUtils = authorityUtils;
    }

    public String join(String email, String password) {

        // 이메일 중복 체크
        userRepository.findByEmail(email)
                .ifPresent(user -> {
                    throw new AppException(ErrorCode.EMAIL_DUPLICATED, "이미 존재하는 이메일 입니다");
                });

        // 저장
        User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .roles(authorityUtils.createRoles(email))
                .build();
        userRepository.save(user);

        return "SUCCESS";
    }
}
