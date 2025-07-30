package com.authuser;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Allow all endpoints
                .allowedOrigins("http://localhost:4200")  // Allow frontend origin
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allow necessary methods
                .allowedHeaders("*");  // Allow all headers
    }
}
