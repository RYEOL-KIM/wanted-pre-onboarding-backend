package com.example.repository;

import com.example.dto.request.PostCreateRequest;
import com.example.dto.request.UserJoinRequest;
import com.example.entity.Post;
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

//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostRepositoryTest {
    @Mock
    private PostRepository postRepository;
    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("게시물 등록 성공")
    void givenCreatePostRequestDto_whenSavingPosts_thenSavesPosts() {
        // Given
        final UserJoinRequest userJoinRequest = UserJoinRequest.builder()
                .email("user1@gmail.com")
                .password("password")
                .build();

        final PostCreateRequest postCreateRequest = PostCreateRequest.builder()
                .title("테스트 게시글 제목 입니다")
                .content("테스트 게시글 내용 입니다")
                .build();

        // When
        final User user = User.of(userJoinRequest.getEmail(), userJoinRequest.getPassword());
        final User savedUser = userRepository.save(user);

        final Post post = Post.of(savedUser, savedUser.getEmail(), postCreateRequest.getTitle(), postCreateRequest.getContent());
        final Post savePost = postRepository.save(post);

        // Then
        assertNotNull(savePost);
        assertTrue(post.getEmail().equals(savePost.getEmail()));
        assertThat(savePost.getId()).isNotNull();
    }
}
