package com.authuser.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authuser.dto.LoginRequest;
import com.authuser.dto.LoginResponse;
import com.authuser.dto.OpenBankRequest;
import com.authuser.dto.TokenRequest;
import com.authuser.service.LoginService;
import com.authuser.service.M2MLoginService;
import com.authuser.service.OpenBankLoginService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final LoginService loginService;
    private final M2MLoginService m2mLoginService;
    private final OpenBankLoginService openBankLoginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse token = loginService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(token);
    }
    
    
    @PostMapping("/m2mToken")
    public ResponseEntity<LoginResponse> token(@RequestBody TokenRequest request) {
        LoginResponse token = m2mLoginService.getAccessToken(request.getClientId(), request.getClientSecret());
        return ResponseEntity.ok(token);
    }
    
    //https://dev-wgk04dj5v68sbhre.us.auth0.com/authorize?response_type=code&client_id=9Gae8MYtMYhtbkLwJ96VGgoOrCuRGgT0&redirect_uri=https%3A%2F%2Fwealthsimple.com%2Fcallback&scope=openid%20profile%20email%20read:accounts&audience=https%3A%2F%2Fmockbank%2Fapi&state=abc123
    @PostMapping("/openBankToken")
    public ResponseEntity<LoginResponse> token(@RequestBody OpenBankRequest request) {
        LoginResponse token = openBankLoginService.getAccessToken(request.getClientId(), request.getClientSecret(), request.getCode(),request.getRedirectUri());
        return ResponseEntity.ok(token);
    }
    
    
    
}
