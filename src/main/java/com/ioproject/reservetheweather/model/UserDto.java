package com.ioproject.reservetheweather.model;

import lombok.Data;

// user data transfer object
@Data
public class UserDto {
    private String name;
    private String mail;
    private String role;
}
