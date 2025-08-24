package com.authuser.dto;

import lombok.AllArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRegistrationRequest {
	  @Email @NotBlank
	  private String email;
	  @NotBlank
	  private String password;
	  @NotBlank
	  private String customerId;
	  @NotBlank
	  private String role; // logical role key (mapped to roleId in config)
}