package com.cjrp.base_001_app_todoist.controller;

import com.cjrp.base_001_app_todoist.dto.AuthResponse;
import com.cjrp.base_001_app_todoist.dto.LoginRequest;
import com.cjrp.base_001_app_todoist.dto.RegisterRequest;
import com.cjrp.base_001_app_todoist.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// (10)
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(
                authService.login(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(
                authService.register(registerRequest));
    }


}
