package com.ioproject.reservetheweather.auth;

import lombok.Data;


/**
 * Klasa reprezentująca żądanie logowania do systemu.
 */
@Data
public class SignInRequest {

    private  String name;
    private String password;
}
