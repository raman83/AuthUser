package com.authuser.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import com.authuser.dto.LoginResponse;


@Service
public class M2MLoginService {

    @Value("${auth0.domain}")
    private String domain;

    @Value("${auth0.loginClientId}")
    private String clientId;

    @Value("${auth0.loginClientSecret}")
    private String clientSecret;

    @Value("${auth0.loginAudience}")
    private String audience;

    private final RestTemplate restTemplate = new RestTemplate();

  
    
    
    
    
    public LoginResponse getAccessToken(String clientId, String clientSecret) {
    	
    	String url = domain + "/oauth/token";

        Map<String, String> request = new java.util.HashMap<>();
        request.put("grant_type", "client_credentials");
        request.put("client_id", clientId);
        request.put("client_secret", clientSecret);
        request.put("audience", audience);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(request, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);
        Map<String, Object> res = response.getBody();

        return new LoginResponse(
                (String) res.get("access_token"),
                (String) res.get("id_token"),
                (String) res.get("token_type"),
                ((Number) res.get("expires_in")).longValue()
        );
    }
    
    
    
    
}
