package com.authuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan(basePackages = {
	    "com.authuser",        // your service's own code
	    "com.authcore.service" // include beans from auth-core
	})
@Import({com.authcore.config.RsaKeyConfig.class})
public class AuthUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthUserApplication.class, args);
	}

}
