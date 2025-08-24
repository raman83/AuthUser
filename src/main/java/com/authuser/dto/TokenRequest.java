package com.authuser.dto;

import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenRequest {
	  @NotBlank

    private String clientId;
	  @NotBlank

    private String clientSecret;
   
}
