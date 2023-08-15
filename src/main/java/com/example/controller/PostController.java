package com.example.controller;

import com.example.dto.request.PostCreateRequest;
import com.example.dto.request.PostPatchRequest;
import com.example.dto.response.PostCreateResponse;
import com.example.dto.response.PostDetailResponse;
import com.example.dto.response.PostMultiResponse;
import com.example.dto.response.PostPatchResponse;
import com.example.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;


    @PostMapping
    public ResponseEntity<PostCreateResponse> createPost(Authentication authentication,
                                                         @RequestBody PostCreateRequest postCreateRequest) {

        return ResponseEntity.ok().body(postService.createPost(authentication.getName(), postCreateRequest));
    }

    @GetMapping("/{post-id}")
    public ResponseEntity<PostDetailResponse> getPost(@PathVariable("post-id") Long postId) {

        return ResponseEntity.ok().body(postService.getPost(postId));
    }

    @GetMapping
    public ResponseEntity<PostMultiResponse> getPosts(@Positive @RequestParam int page,
                                                      @Positive @RequestParam int size) {

        return ResponseEntity.ok().body(postService.getPosts(page, size));
    }

    @PatchMapping("/{post-id}")
    public ResponseEntity<PostPatchResponse> patchPost(Authentication authentication,
                                                       @PathVariable("post-id") Long postId,
                                                       @RequestBody PostPatchRequest postPatchRequest) {
        return ResponseEntity.ok().body(postService.patchPost(postId, authentication.getName(), postPatchRequest));
    }

    @DeleteMapping("/{post-id}")
    public ResponseEntity<String> deletePost(Authentication authentication,
                                             @PathVariable("post-id") Long postId) {
        return ResponseEntity.ok().body(postService.deletePost(postId, authentication.getName()));
    }
}

