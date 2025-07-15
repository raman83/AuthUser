package com.authuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.commons.security.DefaultSecurityConfig;
import com.commons.security.FeignTokenRelayConfig;

@SpringBootApplication
@ComponentScan(basePackages = {
	    "com.authuser",        // your service's own code
	    "com.authcore.service" // include beans from auth-core
	})

@EnableFeignClients(basePackages = "com.authcore.client")
@Import({DefaultSecurityConfig.class, FeignTokenRelayConfig.class})
public class AuthUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthUserApplication.class, args);
	}

}
