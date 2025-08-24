package com.authuser.dto;

import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenBankRequest {
	  @NotBlank

    private String clientId;
	  @NotBlank

    private String clientSecret;
	  @NotBlank

    private String code;
	  @NotBlank

    private String redirectUri;

    
   
}
