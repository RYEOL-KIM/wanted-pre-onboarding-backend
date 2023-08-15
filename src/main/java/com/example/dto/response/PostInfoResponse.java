package com.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostInfoResponse {
    private Long postId;
    private Long userId;
    private String email;
    private String title;
}
