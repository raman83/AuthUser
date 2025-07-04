package com.authuser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authuser.dto.CustomerRegistrationRequest;
import com.authuser.service.CustomerRegistryService;

@RestController
@RequestMapping("/api/v1")
public class CustomerRegistrationController {
	
	
	@Autowired
	CustomerRegistryService service;
	
	@PostMapping("/customer/register")
	public ResponseEntity<Void> registerCustomer(@RequestBody CustomerRegistrationRequest request) {
	    service.registerCustomer(request);
	    return ResponseEntity.ok().build();
	}

}
