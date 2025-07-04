package com.authuser.service;

import org.springframework.stereotype.Service;

import com.authcore.dto.TokenResponse;
import com.authcore.service.JwtTokenService;
import com.authuser.dto.LoginRequest;
import com.authuser.entity.CustomerCredential;
import com.authuser.repository.CustomerCredentialRepository;
import com.nimbusds.jose.JOSEException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
	
	
	private final CustomerCredentialRepository repository;
	private final JwtTokenService jwtService;
	
	
	
	public TokenResponse login(LoginRequest request) throws JOSEException {
		CustomerCredential creds = repository.findByEmail(request.getEmail())
	        .orElseThrow(() -> new RuntimeException("User not found"));

	    if (!request.getPassword().equals(creds.getPasswordHash())) {
	        throw new RuntimeException("Invalid credentials");
	    }

	    TokenResponse token = jwtService.generateToken("firstparty-client",creds.getCustomerId(), creds.getRole());
	    return token;
	}

}
