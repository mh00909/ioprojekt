package com.ioproject.reservetheweather.auth;


import lombok.Data;

@Data
public class SignUpRequest {
    private String name;
    private String mail;
    private String password;
    private long phoneNumber;
}
