package com.authuser.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import com.authuser.dto.LoginResponse;


@Service
public class LoginService {

    @Value("${auth0.domain}")
    private String domain;

    @Value("${auth0.loginClientId}")
    private String clientId;

    @Value("${auth0.loginClientSecret}")
    private String clientSecret;

    @Value("${auth0.loginAudience}")
    private String audience;

    private final RestTemplate restTemplate = new RestTemplate();

    public LoginResponse login(String username, String password) {
    	String url = domain + "/oauth/token";

        Map<String, String> body = new java.util.HashMap<>();
        body.put("grant_type", "http://auth0.com/oauth/grant-type/password-realm");
        body.put("username", username);
        body.put("password", password);
        body.put("audience", audience);
        body.put("scope", "openid profile email");
        body.put("client_id", clientId);
        body.put("client_secret", clientSecret);
        body.put("realm", "Username-Password-Authentication");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

        Map<String, Object> res = response.getBody();

        return new LoginResponse(
                (String) res.get("access_token"),
                (String) res.get("id_token"),
                (String) res.get("token_type"),
                ((Number) res.get("expires_in")).longValue()
        );
    }
    
    
   
    
    
    
    
}
