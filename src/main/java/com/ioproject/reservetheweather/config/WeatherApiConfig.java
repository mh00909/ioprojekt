package com.ioproject.reservetheweather.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WeatherApiConfig {
    @Value("${openweathermap.api.key}")
    private String openWeatherMapApiKey;
    @Bean
    public String openWeatherMapApiKey() {
        return openWeatherMapApiKey;
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}