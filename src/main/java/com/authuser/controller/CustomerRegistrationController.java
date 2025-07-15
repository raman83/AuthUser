package com.authuser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.exception.Auth0Exception;
import com.authuser.dto.CustomerRegistrationRequest;
import com.authuser.service.CustomerRegistrationService;

@RestController
@RequestMapping("/api/v1")
public class CustomerRegistrationController {
	
	
	@Autowired
	CustomerRegistrationService service;
	
	@PostMapping("/customer/register")
	public ResponseEntity<Void> registerCustomer(@RequestBody CustomerRegistrationRequest request) throws Auth0Exception {
	    service.registerCustomer(request);
	    return ResponseEntity.ok().build();
	}

}
