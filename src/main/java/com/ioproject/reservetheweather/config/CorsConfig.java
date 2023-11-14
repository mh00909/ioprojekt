package com.ioproject.reservetheweather.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/*
@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api")
                .allowedOrigins("http://localhost:3000", "http://localhost:3001", "http://localhost:3002",
                        "http://localhost:3003", "http://localhost:3004", "http://localhost:3005") // Adres frontendu
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(true)
                .maxAge(3600);
        registry.addMapping("/login")
                .allowedOrigins("http://localhost:3000", "http://localhost:3001", "http://localhost:3002",
                        "http://localhost:3003", "http://localhost:3004", "http://localhost:3005") // Adres frontendu
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(true)
                .maxAge(3600);



    }
}




 */