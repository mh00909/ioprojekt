package com.ioproject.reservetheweather.auth;

import lombok.Data;

@Data
public class SignInRequest {

    private  String name;
    private String password;
}
