package com.example.repository;

import com.example.dto.request.UserJoinRequest;
import com.example.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("회원가입 성공")
    public void savedUserTest() {
        //given
        final UserJoinRequest userJoinRequest = UserJoinRequest.builder()
                .email("user1@gmail.com")
                .password("password")
                .build();

        //when
        final User user = User.of(userJoinRequest.getEmail(), userJoinRequest.getPassword());
        final User savedUser = userRepository.save(user);

        //then
        assertNotNull(savedUser);
        assertTrue(user.getEmail().equals(savedUser.getEmail()));
        assertThat(savedUser.getId()).isNotNull();
    }
}
