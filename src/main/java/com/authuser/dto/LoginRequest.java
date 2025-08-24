package com.authuser.dto;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
public class LoginRequest {
	  @Email @NotBlank
    private String username;
	  @NotBlank

    private String password;
}