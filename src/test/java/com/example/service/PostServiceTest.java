package com.example.service;

import com.example.dto.request.PostCreateRequest;
import com.example.dto.response.PostCreateResponse;
import com.example.entity.Post;
import com.example.entity.User;
import com.example.repository.PostRepository;
import com.example.repository.UserRepository;
import jdk.jfr.Name;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.security.Principal;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PostServiceTest {

    @InjectMocks
    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("게시물 등록 성공")
    void test_createPost() {
        // Given
        PostCreateRequest postCreateRequest = PostCreateRequest.builder()
                .title("테스트 게시글 제목입니다")
                .content("테스트 게시글 내용입니다")
                .build();
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("user1@gmail.com");

        User user = User.of("user1@gmail.com", "password");
        when(userRepository.findByEmail(authentication.getName())).thenReturn(Optional.of(user));

        Post createdPost = new Post(user, "user1@gmail.com", "테스트 게시글 제목입니다", "테스트 게시글 내용입니다");
        when(postRepository.save(any(Post.class))).thenReturn(createdPost);

        // When
        PostCreateResponse result = postService.createPost(authentication.getName(), postCreateRequest);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("테스트 게시글 제목입니다");
    }
}
