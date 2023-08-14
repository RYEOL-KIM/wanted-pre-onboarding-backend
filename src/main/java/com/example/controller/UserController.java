package com.example.controller;

import com.example.dto.UserJoinRequest;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<String> join(@Valid @RequestBody UserJoinRequest dto) {
        userService.join(dto.getEmail(), dto.getPassword());
        return ResponseEntity.ok().body("회원가입이 성공적으로 이루어졌습니다.");
    }
}
