package com.authuser.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import com.authuser.dto.LoginResponse;


@Service
public class OpenBankLoginService {

	  @Value("${auth0.domain}")
	    private String domain;


    private final RestTemplate restTemplate = new RestTemplate();

  
    
    
    
    
    public LoginResponse getAccessToken(String clientId, String clientSecret, String code , String redirectUri) {
    	String url = domain + "/oauth/token";

       
    	Map<String, String> request = new HashMap<>();
        request.put("grant_type", "authorization_code");
        request.put("client_id", clientId);
        request.put("client_secret", clientSecret);
        request.put("code", code);
        request.put("redirect_uri", redirectUri);

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
