package com.tweelon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // allow CORS for all endpoints
                .allowedOrigins("http://localhost:4200") // allow this origin angular
                .allowedMethods("GET", "POST", "PUT", "DELETE") // allow these HTTP methods
                .allowCredentials(true) // allow sending of cookies and HTTP authentication information
                .maxAge(3600); // how long the response from a pre-flight request can be cached
    }
}
