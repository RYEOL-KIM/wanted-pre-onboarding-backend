package com.example.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostPatchRequest {
    private String title;
    private String content;
}
