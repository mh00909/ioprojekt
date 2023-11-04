package com.ioproject.reservetheweather.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ioproject.reservetheweather.model.WeatherData;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    private final String openWeatherMapApiKey;
    private final RestTemplate restTemplate;

    String city = "Krakow";
    public WeatherService(String openWeatherMapApiKey, RestTemplate restTemplate) {
        this.openWeatherMapApiKey = openWeatherMapApiKey;
        this.restTemplate = restTemplate;
    }

    public WeatherData checkWeather() {
        try {
            String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + openWeatherMapApiKey
                    + "&units=metric";


            String json = restTemplate.getForObject(apiUrl, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            WeatherData weatherData = objectMapper.readValue(json, WeatherData.class);


            return weatherData;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
