package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Pattern;

@AllArgsConstructor
@Getter
public class UserLoginRequest {
    private String email;
    private String password;
}
