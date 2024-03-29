package com.gauth.practice.domain.auth.presentation;

import com.gauth.practice.domain.auth.presentation.dto.request.SignInRequest;
import com.gauth.practice.domain.auth.presentation.dto.response.TokenResponse;
import com.gauth.practice.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("signin")
    public ResponseEntity<TokenResponse> signUp(SignInRequest signInRequest) {
        authService.signIn(signInRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
