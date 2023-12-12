package com.ioproject.reservetheweather.model;


import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class EventDto {
    private Long id;
    private String name;
    private String location;
    private double price;
    @DateTimeFormat(pattern = "HH:mm")  private LocalTime time;
    @DateTimeFormat(pattern = "yyyy-MM-dd")  private LocalDate date;
    private int duration;
    private String description;
    private int maxUsers;
    public int signedUsers;
    public List<User> users;
    double minTemperature;
    double maxTemperature;

    public boolean badWeather = false;
    public boolean discount = false;
}
