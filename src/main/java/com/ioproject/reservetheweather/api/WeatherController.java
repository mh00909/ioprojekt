package com.ioproject.reservetheweather.api;

import com.ioproject.reservetheweather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;

public class WeatherController {
    @Autowired
    private WeatherService weatherService;
}
