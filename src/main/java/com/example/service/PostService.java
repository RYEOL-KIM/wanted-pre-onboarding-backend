package com.example.service;

import com.example.dto.request.PostCreateRequest;
import com.example.dto.request.PostPatchRequest;
import com.example.dto.response.*;
import com.example.entity.Post;
import com.example.entity.User;
import com.example.exception.AppException;
import com.example.exception.ErrorCode;
import com.example.repository.PostRepository;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public PostCreateResponse createPost(String email, PostCreateRequest dto) {
        User user = userRepository.findByEmail(email).get();

        Post post = Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .email(user.getEmail())
                .user(user)
                .build();

        Post savedPost = postRepository.save(post);

        return PostCreateResponse.builder()
                .postId(savedPost.getId())
                .userId(savedPost.getUser().getId())
                .email(savedPost.getEmail())
                .title(savedPost.getTitle())
                .content(savedPost.getContent())
                .message("게시글 등록에 성공하였습니다")
                .build();
    }

    public PostDetailResponse getPost(Long postId) {
        Post post = postRepository.findById(postId).get();

        return PostDetailResponse.builder()
                .postId(post.getId())
                .userId(post.getUser().getId())
                .email(post.getEmail())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }

    public PostMultiResponse getPosts(int page, int size) {
        Page<Post> postPage = postRepository.findAll(PageRequest.of(page - 1, size));
        List<PostInfoResponse> posts = postPage.getContent()
                .stream()
                .map(post -> new PostInfoResponse(
                        post.getId(),
                        post.getUser().getId(),
                        post.getEmail(),
                        post.getTitle()))
                .collect(Collectors.toList());

        return new PostMultiResponse<>(posts, postPage);
    }

    public PostPatchResponse patchPost(Long postId, String email, PostPatchRequest postPatchRequest) {
        // 게시글 작성자 확인

        Post post = postRepository.findById(postId).get();

        verifiedPostOwner(post.getUser().getId(), email);

        post.updateTitle(postPatchRequest.getTitle());
        post.updateContent(postPatchRequest.getContent());

        Post savedPost = postRepository.save(post);

        return PostPatchResponse.builder()
                .postId(savedPost.getId())
                .email(savedPost.getEmail())
                .title(savedPost.getTitle())
                .content(savedPost.getContent())
                .message("게시글 수정에 성공하였습니다")
                .build();
    }

    public String deletePost(Long postId, String email) {

        Post post = postRepository.findById(postId).get();

        verifiedPostOwner(post.getUser().getId(), email);

        postRepository.delete(post);

        return "게시물 삭제에 성공하였습니다";
    }

    public void verifiedPostOwner(Long userId, String email) {
        User user = userRepository.findByEmail(email).get();

        if(!(userId == user.getId())) {
            throw new AppException(ErrorCode.NOT_AUTHOR, "작성자가 아닙니다");
        }
    }
}
