package com.example.service;

import com.example.dto.request.UserJoinRequest;
import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.utils.CustomAuthorityUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private CustomAuthorityUtils authorityUtils;

    @Test
    @DisplayName("회원가입 성공")
    void test_sign_up_success() {
        // Given
        final UserJoinRequest userJoinRequest = UserJoinRequest.builder()
                .email("user1@gmail.com")
                .password("password")
                .build();

        when(userRepository.findByEmail(userJoinRequest.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(userJoinRequest.getPassword())).thenReturn("encodedPassword");
        when(authorityUtils.createRoles(userJoinRequest.getEmail())).thenReturn(List.of("USER"));
        when(userRepository.save(any(User.class))).thenReturn(User.of(userJoinRequest.getEmail(), userJoinRequest.getPassword()));


        // When
        User resultUser = userService.join(userJoinRequest.getEmail(), userJoinRequest.getPassword());

        // Then
        assertThat(resultUser).isNotNull();
        verify(userRepository).findByEmail(userJoinRequest.getEmail());
        verify(passwordEncoder).encode(userJoinRequest.getPassword());
        verify(authorityUtils).createRoles(userJoinRequest.getEmail());
        verify(userRepository).save(any(User.class));
    }
}
